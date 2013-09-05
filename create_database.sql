create table if not exists public.writings
(
    id                       serial
  , id_author                int4
  , n_total_sentences        int4
  , n_total_words            int4
  , n_total_alpha_chars      int4
  , n_unique_words           int4
  , mean_words_per_sentence  float4
  , mean_characters_per_word float4
  , primary key( id ) 
)
with( oids = false );
drop index if exists writings_author;
create index writings_author on public.writings using btree( id_author );

    
create table if not exists public.authors
(
    id         serial
  , name       varchar( 255 )
  , primary key( id )
)
with( oids = false );
      
drop index if exists authors_names;
create index authors_names on public.authors using btree( name );