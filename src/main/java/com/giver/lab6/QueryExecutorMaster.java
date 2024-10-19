package com.giver.lab6;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryExecutorMaster {
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    public static final String DB_CREATION_QUERY = "CREATE DATABASE music_store;";
    public static final String TABLE_CREATION_QUERY = """
            CREATE TABLE public.artists (
                id SERIAL PRIMARY KEY,
                name VARCHAR(255) NOT NULL
            );
            CREATE TABLE public.albums (
                id SERIAL PRIMARY KEY,
                name VARCHAR(255),
                genre VARCHAR(255) NOT NULL,
                artist_id INT NOT NULL,
                FOREIGN KEY (artist_id) REFERENCES artists(id)
            );
            CREATE TABLE public.tracks (
                id SERIAL PRIMARY KEY,
                name VARCHAR(255) NOT NULL,
                length INT NOT NULL,
                album_id INT,
                FOREIGN KEY (album_id) REFERENCES albums(id)
            );
            """;
    public static final String INSERT_ARTISTS_QUERY =
            "INSERT INTO public.artists (name) VALUES " +
                    "('The Beatles'), " +
                    "('Queen'), " +
                    "('Pink Floyd'), " +
                    "('Metallica'), " +
                    "('Led Zeppelin')";

    public static final String INSERT_ALBUMS_QUERY =
            "INSERT INTO public.albums (name, genre, artist_id) VALUES " +
                    "('Abbey Road', 'Rock', 1), " +
                    "('A Night at the Opera', 'Rock', 2), " +
                    "('The Dark Side of the Moon', 'Progressive Rock', 3), " +
                    "('Master of Puppets', 'Heavy Metal', 4), " +
                    "('Celebration Day', 'Hard Rock', 5)";
    public static final String INSERT_TRACKS_QUERY =
            "INSERT INTO public.tracks (name, length, album_id) VALUES " +
                    "('Come together',259,1), " +
                    "('Something',182,1), " +
                    "('Death on two legs',223,2), " +
                    "('Sweet lady',242,2), " +
                    "('Speak to me',64,3), " +
                    "('Breathe',169,3), " +
                    "('Battery',312,4), " +
                    "('Welcome home',387,4), " +
                    "('Ramble on',344,5), " +
                    "('For your life',400,5)";
    public static final String DELETING_SONG_QUERY =
            "DELETE FROM public.tracks WHERE id = 5;";
    public static final String CHANGING_ALBUM_QUERY =
            "UPDATE public.albums SET name = 'vladimirskiy central' WHERE id = 5;";
    public static final String SELECT_TRACK_QUERY =
            "SELECT public.albums.name AS AlbumName, MIN(public.tracks.length) AS Duration " +
                    "FROM public.albums " +
                    "JOIN public.tracks ON public.albums.id = public.tracks.album_id " +
                    "GROUP BY public.albums.id " +
                    "HAVING MIN(public.tracks.length) >= 5";


    public void noResultOperationExecution(String query, String db_name){
        try{
            connection = JDBCConnector.getConnection(db_name);
            statement = connection.createStatement();
            statement.execute(query);
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public ResultSet hasResultOperationExecution(String query, String db_name){
        try{
            connection = JDBCConnector.getConnection(db_name);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return resultSet;
    }

}
