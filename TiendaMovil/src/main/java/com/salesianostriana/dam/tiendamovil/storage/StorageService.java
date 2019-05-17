//package com.salesianostriana.dam.tiendamovil.storage;
//
//import org.springframework.core.io.Resource;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.nio.file.Path;
//import java.util.stream.Stream;
//
///** No se pone el tipo de acceso por comidad, se deja el acceso de paquete 
// * (por defecto) ya que en el ejemplo no hace falta ningún tipo de acceso 
// * más amplio,, pordrían ser public como siempre*/
//
//public interface StorageService {
//
//    void init();
//
//    String store(MultipartFile file);
//
//    Stream<Path> loadAll();
//
//    Path load(String filename);
//
//    Resource loadAsResource(String filename);
//
//    void deleteAll();
//
//}
