package com.example.mangastorecliente.DAO;

import com.example.mangastorecliente.Model.Manga;
import java.util.List;
import java.util.Optional;

public interface IMangaDAO {
        Manga create(Manga manga);

        Manga update(Manga manga);

        void delete(Manga login);

        List<Manga> findAll();

        Optional<Manga> findById(int id);
}
