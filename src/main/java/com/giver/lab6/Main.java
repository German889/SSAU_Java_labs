package com.giver.lab6;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        QueryExecutorMaster executorMaster = new QueryExecutorMaster();
        executorMaster.noResultOperationExecution(QueryExecutorMaster.DB_CREATION_QUERY,"");
        executorMaster.noResultOperationExecution(QueryExecutorMaster.TABLE_CREATION_QUERY,"music_store");
        executorMaster.noResultOperationExecution(QueryExecutorMaster.INSERT_ARTISTS_QUERY,"music_store");
        executorMaster.noResultOperationExecution(QueryExecutorMaster.INSERT_ALBUMS_QUERY,"music_store");
        executorMaster.noResultOperationExecution(QueryExecutorMaster.INSERT_TRACKS_QUERY,"music_store");
        ResultSet rs = null;
        rs = executorMaster.hasResultOperationExecution(QueryExecutorMaster.SELECT_TRACK_QUERY,"music_store");
        executorMaster.noResultOperationExecution(QueryExecutorMaster.CHANGING_ALBUM_QUERY,"music_store");
        executorMaster.noResultOperationExecution(QueryExecutorMaster.DELETING_SONG_QUERY,"music_store");

        StringBuilder resultString = new StringBuilder();
        resultString.append("AlbumName | Duration\n");
        resultString.append("---------------------\n");
        try{
            while (rs.next()) {
                String albumName = rs.getString("AlbumName");
                int duration = rs.getInt("Duration");

                // Формируем строку с результатами
                resultString.append(albumName)
                        .append(" | ")
                        .append(duration)
                        .append(" seconds\n");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        System.out.println(resultString.toString());

    }
}
