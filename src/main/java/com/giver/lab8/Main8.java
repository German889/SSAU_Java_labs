// This is a personal academic project. Dear PVS-Studio, please check it.
// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: https://pvs-studio.com
package com.giver.lab8;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Main8 {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            addAuthors(session, "skillet", "leader","adam lambert");
            addTracksInAlbum(session, 34, "Victorious", "rock", "skillet", "rise up", "legendary","anchor");
            editTrack(session, "legendary", "truth");
            deleteTrack(session, "anchor");
            showTracks(session);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    public static void addAuthors(Session session, String... names){
        for(String s:names){
            Artist a = new Artist();
            a.setName(s);
            session.save(a);
        }
    }
    public static void addTracksInAlbum(Session session, int length, String albumName, String genre, String artistName, String... names) {
        // Попробуйте найти существующего Artist по имени
        Query<Artist> artistQuery = session.createQuery("FROM Artist a WHERE a.name = :name", Artist.class);
        artistQuery.setParameter("name", artistName);
        List<Artist> artistList = artistQuery.getResultList();
        Artist artist;

        // Если Artist найден, используем его, иначе создаем нового
        if (!artistList.isEmpty()) {
            artist = artistList.get(0);
        } else {
            artist = new Artist();
            artist.setName(artistName);
            session.save(artist);
        }

        // Создаем и сохраняем треки
        List<Track> tracks = new ArrayList<>();
        for (String s : names) {
            Track t = new Track(length, null, s);
            tracks.add(t);
            session.save(t);
        }

        // Создаем альбом и связываем с Artist и треками
        Album album = new Album(albumName, genre, artist, tracks);
        session.save(album);
    }
    public static void editTrack(Session session, String oldName, String newName){
        Query<Track> q = session.createQuery("FROM Track t WHERE t.name = :name", Track.class);
        q.setParameter("name", oldName);
        List<Track> tl = q.getResultList();
        Track found = tl.get(0);
        found.setName(newName);
        session.update(found);
    }

    public static void deleteTrack(Session session, String name) {
        Query<Track> q = session.createQuery("FROM Track t WHERE t.name = :name", Track.class);
        q.setParameter("name", name);
        List<Track> tl = q.getResultList();

        if (!tl.isEmpty()) {
            Track found = tl.get(0);
            Album album = found.getAlbum();
            if (album != null) {
                album.getTracks().remove(found);
            }

            session.delete(found);
        }
    }

    public static void showTracks(Session session){
        Query<Track> q = session.createQuery("from Track", Track.class);
        List<Track> tracks = q.getResultList();
        System.out.println("===============================вот все песенки=====================================");
        for(Track t:tracks){
            System.out.println(t.getName());
        }
    }
}
