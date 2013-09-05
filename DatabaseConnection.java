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

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection
{
  Connection _dbh;
  
  public DatabaseConnection()
  {
    _dbh = null;
    try {
      Class.forName( "org.postgresql.Driver" );
      Properties conn_prop = new Properties();
      conn_prop.setProperty( "user", "htnkjeetnanrjz" );
      conn_prop.setProperty( "password", "0ydqgP3y7hYIqy9RoFRSDs-1Wu" );
      _dbh = DriverManager.getConnection( "jdbc:postgresql://ec2-54-221-196-140.compute-1.amazonaws.com:5432/ds8msskf3bt0s"
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
  
  public void createTables()
  {
    // create table if not exists public.writings (
    //     id                       int4 not null
    //   , id_author                int4
    //   , n_total_sentences        int4
    //   , n_total_words            int4
    //   , n_total_alpha_chars      int4
    //   , n_unique_words           int4
    //   , mean_words_per_sentence  float4
    //   , mean_characters_per_word float4
    //   , primary key( "id" )
    // )
    // with( oids = false );
    // create unique index "pkey" on public.writings using btree( "id" );
    // create index "author" on public.writings using btree( "id_author" );

    // create table if not exists public.authors ( 
    //     id         int4  not NULL
    //   , name       varchar( 255 ) 
    //   , primary key( "id" )
    // )
    // with( oids = false )
    // create unique index "pkey" on public.writings using btree( "id" );
    // create index "names" on public.writings using btree( "name" );
  }
  
  public void dropTables()
  {
    // drop table if exists public.writings
  }
  
  public void fetchWritingsAll() 
  {
    // select * from public.writings
  }
  
  public void fetchWritingsByAuthor( int author_id )
  {
    // select * from public.writings where id_author = :author_id:
  }
  
  public void fetchWritingsByAuthor( String author_name )
  {
    // select 
    //     public.writings.* 
    // from 
    //  public.writings 
    //  join public.authors on public.writings.id_author = public.authors.id
    // where
    //   public.authors.name = :author_name:
  }
  
  /**
   * 
   * @return primary key of inserted row
   */
  // I sort of think this info could be encapsulated to avoid polymorph methods
  public int insertWriting( int author_id
                          , int n_total_sequences
                          , int n_total_words
                          , int n_total_alpha_chars
                          , int n_unique_words
                          , int mean_words_per_sentence
                          , int mean_characters_per_word )
  {
    // insert into public.writings
    return 0;
  }
  
  /**
   * 
   * @return primary key of inserted row
   */
  public int insertWriting( String author_name
                          , int n_total_sequences
                          , int n_total_words
                          , int n_total_alpha_chars
                          , int n_unique_words
                          , int mean_words_per_sentence
                          , int mean_characters_per_word )
  {
    // insert into public.writings
    return 0;
  }
  
  public void deleteWriting( int writing_id )
  {
    // delete from public.writings where id = :writing_id: 
  }
}
