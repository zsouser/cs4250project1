// to link the postgresql jdbc drivers, afaik:
// the postgres server hosted is 9.2 and not 9.3 but I think
// the postgres jdbc is backwards compat according to postgresql docs??
// the goal is to add the postgresql.jar library into the java classpath
//
// system-wide:
// one time: set CLASSPATH=\path\to\postgres.jar:.
// forever: export CLASSPATH=\path\to\postgres.jar:.
// 
// command line arguments upon execution:
// java -cp \path\to\postgresql.jar:. classname
// 
// eclipse:
// project --> properties --> java build path --> libraries --> add extern jar

// notes for meeting tomorrow:
// todo( mathew guest ):
// I dont think any of this generation code should be in java
// I think that maybe it should be .sql files and handled by the "sysadmin"
// I'm not sure how to invoke that on heroku though

// One can't close the statements before returning because that would
// free the ResultSet.

// If we have the totals, we could just compute means in sql.
// but if we're doing a lot of statistical calculation, maybe we 
// should be using a statistical calculator like SAS or R?

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DatabaseConnection
{
  Connection _dbh;
  
  public DatabaseConnection()
  {
    // the heroku documentation connects a little better, like:
    // URI dbUri = new URI(System.getenv("DATABASE_URL"));
    // String username = dbUri.getUserInfo().split(":")[0];
    // String password = dbUri.getUserInfo().split(":")[1];
    // String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
    // return DriverManager.getConnection(dbUrl, username, password);

    _dbh = null;
    
    String username, passwd;
    username = null;
    passwd = null;
    BufferedReader buff;
    try {
      buff = new BufferedReader( new FileReader( "db_credentials.txt" ) );
      String line;
      if( (line = buff.readLine()) == null ) {
        System.out.println( "bad credentials file format" );
        buff.close();
        return;
      }
      username = line;
      if( (line = buff.readLine()) == null ) {
        System.out.println( "bad credentials file format" );
        buff.close();
        return;
      }
      passwd = line;
      buff.close();
    } catch( IOException ex ) {
      return;
    }

    if( username == null|| passwd == null )
      return;
    // todo( mathew guest ): can't figure out how to close in a finally?
    
    
    try {
      Class.forName( "org.postgresql.Driver" );
      Properties conn_prop = new Properties();
      conn_prop.setProperty( "user", username );
      conn_prop.setProperty( "password", passwd );
      // conn_prop.setProperty( "loglevel", "2" );
      
      // for remote connections, heroku requires ssl 
      conn_prop.setProperty( "ssl", "true" );
      conn_prop.setProperty( "sslfactory", "org.postgresql.ssl.NonValidatingFactory" );
      
      _dbh = DriverManager.getConnection( "jdbc:postgresql://ec2-54-221-196-140.compute-1.amazonaws.com:5432/ds8msskf3bt0s?username="+username+"&password="+passwd
                                        , conn_prop );
      
    } catch( ClassNotFoundException ex ) {
      // postgres jdbc driver not linked in correctly
      System.err.println( ex );
    } catch( SQLException ex ) {
      // connection attempt failed
      // postgres db host/credentials not working
      System.err.println( ex );
    }
  }
  
  public void close() throws SQLException
  {
    if( _dbh == null ) return;
    _dbh.close();
  }
  
  public void createTables() throws SQLException
  {
    if( _dbh == null ) return;
    PreparedStatement stmt;
    String sql;
    
    sql = "create table if not exists public.writings"
        + "("
        + "    id                       serial"
        + "  , id_author                int4"
        + "  , n_total_sentences        int4"
        + "  , n_total_words            int4"
        + "  , n_total_alpha_chars      int4"
        + "  , n_unique_words           int4"
        + "  , mean_words_per_sentence  float4"
        + "  , mean_characters_per_word float4"
        + "  , primary key( id ) "
        + ")"
        + "with( oids = false );"
        
        + "drop index if exists writings_author;"
        + "create index writings_author on public.writings using btree( id_author );";
    stmt = _dbh.prepareStatement( sql );
    stmt.executeUpdate();
    stmt.close();
    
    sql = "create table if not exists public.authors"
        + "("
        + "    id         serial"
        + "  , name       varchar( 255 )"
        + "  , primary key( id )"
        + ")"
        + "with( oids = false );"
      
        + "drop index if exists authors_names;"
        + "create index authors_names on public.authors using btree( name );";
    stmt = _dbh.prepareStatement( sql );
    stmt.executeUpdate();
    stmt.close();
  }
  
  public void dropTables() throws SQLException
  {
    if( _dbh == null ) return;
    PreparedStatement stmt;
    String sql;
    sql = "drop table if exists public.writings;"
        + "drop table if exists public.authors;";
    stmt = _dbh.prepareStatement( sql );
    stmt.executeUpdate();
    stmt.close();
  }
    
  public ResultSet fetchWritingsAll() throws SQLException 
  {
    if( _dbh == null ) return null;
    Statement stmt = _dbh.createStatement();
    ResultSet rslts = stmt.executeQuery( "select * from public.writings" );
    return rslts;
  }
  
  public ResultSet fetchWritingsByAuthor( int id_author ) throws SQLException
  {
    if( _dbh == null ) return null;
    String sql;
    PreparedStatement stmt;
    sql = "select * from public.writings where id_author = ?" ;
    stmt = _dbh.prepareStatement( sql );
    org.postgresql.PGStatement pgstmt = (org.postgresql.PGStatement)stmt;
    pgstmt.setPrepareThreshold(1);
    stmt.setInt( 1, id_author );
    return stmt.executeQuery();
  }
  
  public ResultSet fetchWritingsByAuthor( String author_name ) throws SQLException
  {
    if( _dbh == null ) return null;
    String sql;
    PreparedStatement stmt;
    sql = "select "
        + "    * "
        + "from"
        + "  public.writings"
        + "  join public.authors"
        + "where"
        + "  author.name = ?" ;
    stmt = _dbh.prepareStatement( sql );
    org.postgresql.PGStatement pgstmt = (org.postgresql.PGStatement)stmt;
    pgstmt.setPrepareThreshold(1);
    stmt.setString( 1, author_name );
    return stmt.executeQuery();
  }
  
  /**
   * @return primary key of inserted row and -1 otherwise
   * @throws SQLException 
   */
  // I sort of think this info could be encapsulated to avoid polymorph methods
  public int insertWriting( int id_author
                          , int n_total_sentences
                          , int n_total_words
                          , int n_total_alpha_chars
                          , int n_unique_words
                          , int mean_words_per_sentence
                          , int mean_characters_per_word ) throws SQLException
  {
    if( _dbh == null ) return -1;
    String sql;
    PreparedStatement stmt;
    sql = "insert into public.writings"
        + "(    id"
        + "   , id_author"
        + "   , n_total_sentences"
        + "   , n_total_words"
        + "   , n_total_alpha_chars"
        + "   , n_unique_words"
        + "   , mean_words_per_sentence"
        + "   , mean_characters_per_word"
        + ")"
        + "values"
        + "( default, ?, ?, ?, ?, ?, ?, ? )"
        + "returning id;";
    stmt = _dbh.prepareStatement( sql );
    org.postgresql.PGStatement pgstmt = (org.postgresql.PGStatement)stmt;
    pgstmt.setPrepareThreshold(1);
    stmt.setInt( 1, id_author );
    stmt.setInt( 2, n_total_sentences );
    stmt.setInt( 3, n_total_words );
    stmt.setInt( 4, n_total_alpha_chars );
    stmt.setInt( 5, n_unique_words );
    stmt.setFloat( 6, mean_words_per_sentence );
    stmt.setFloat( 7, mean_characters_per_word );
    ResultSet rslts = stmt.executeQuery();
    if( rslts.next() ) 
      return rslts.getInt(1);
    return -1;
  }
  
  /**
   * @return primary key of inserted row
   * @throws SQLException 
   */
  public int insertWriting( String author_name
                          , int n_total_sentences
                          , int n_total_words
                          , int n_total_alpha_chars
                          , int n_unique_words
                          , int mean_words_per_sentence
                          , int mean_characters_per_word ) throws SQLException
  {
    if( _dbh == null ) return -1;
    String sql;
    PreparedStatement stmt;
    sql = "insert into public.writings"
        + "(    id"
        + "   , id_author"
        + "   , n_total_sentences"
        + "   , n_total_words"
        + "   , n_total_alpha_chars"
        + "   , n_unique_words"
        + "   , mean_words_per_sentence"
        + "   , mean_characters_per_word"
        + ")"
        + "values"
        + "(   default"
        + "  , (select id from public.authors where name = ? limit 1)"
        + "  , ?"
        + "  , ?"
        + "  , ?"
        + "  , ?"
        + "  , ?"
        + "  , ?"
        + ")"
        + "returning id;";
    stmt = _dbh.prepareStatement( sql );
    org.postgresql.PGStatement pgstmt = (org.postgresql.PGStatement)stmt;
    pgstmt.setPrepareThreshold(1);
    stmt.setString( 1, author_name );
    stmt.setInt( 2, n_total_sentences );
    stmt.setInt( 3, n_total_words );
    stmt.setInt( 4, n_total_alpha_chars );
    stmt.setInt( 5, n_unique_words );
    stmt.setFloat( 6, mean_words_per_sentence );
    stmt.setFloat( 7, mean_characters_per_word );
    ResultSet rslts = stmt.executeQuery();
    if( rslts.next() ) 
      return rslts.getInt(1);
    return -1;
  }
  
  public void deleteWriting( int id_writing ) throws SQLException
  {
    if( _dbh == null ) return;
    String sql;
    PreparedStatement stmt;
    sql = "delete from public.writings where id = ?" ;
    stmt = _dbh.prepareStatement( sql );
    org.postgresql.PGStatement pgstmt = (org.postgresql.PGStatement)stmt;
    pgstmt.setPrepareThreshold(1);
    stmt.setInt( 1, id_writing );
    stmt.executeQuery();
  }
}
