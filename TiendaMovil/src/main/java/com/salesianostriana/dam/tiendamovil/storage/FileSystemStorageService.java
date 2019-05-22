package com.salesianostriana.dam.tiendamovil.storage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * Toda esta clase y el resto de este paquete son clases necesarias para la
 * gestión del almacenamiento. No forman parte de lo que damos en este curso,
 * por lo que se puede añadir a nuestro proyecto para usarla y ya está. Los
 * métodos están relacionados con la gestión de objetos MultipartFile, almacenar
 * con un nombre que incluya la hora para que no se puedan guardar dos archivos
 * con el mismo nombre, borrar, etc.
 */

@Service
public class FileSystemStorageService implements StorageService {

	private final Path rootLocation;

	@Autowired
	public FileSystemStorageService(StorageProperties properties) {
		this.rootLocation = Paths.get(properties.getLocation());
	}

	@Override
	public String store(MultipartFile file) {
		try {
			if (file.isEmpty()) {
				throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
			}
			// Nombre original del fichero
			String originalFileName = file.getOriginalFilename();
			// Obtenemos la fecha y hora actual del sistema (antiguo)
			DateFormat df = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
			// El nombre del fichero será dd-MM-yyyy_HH-mm-ss_originalFileName.ext
			String fileName = df.format(new Date()) + "_" + originalFileName;
			// Obtenemos el path completo para realizar el almacenamiento
			Path path = this.rootLocation.resolve(fileName);
			Files.copy(file.getInputStream(), path);
			// Devolvemos el nombre del fichero, para almacenarlo donde corresponda
			return fileName;
		} catch (IOException e) {
			throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
		}

	}

	@Override
	public Stream<Path> loadAll() {
		try {
			return Files.walk(this.rootLocation, 1).filter(path -> !path.equals(this.rootLocation))
					.map(path -> this.rootLocation.relativize(path));
		} catch (IOException e) {
			throw new StorageException("Failed to read stored files", e);
		}

	}

	@Override
	public Path load(String filename) {
		return rootLocation.resolve(filename);
	}

	@Override
	public Resource loadAsResource(String filename) {
		try {
			Path file = load(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new StorageFileNotFoundException("Could not read file: " + filename);

			}
		} catch (MalformedURLException e) {
			throw new StorageFileNotFoundException("Could not read file: " + filename, e);
		}
	}

	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}

	@Override
	public void init() {
		try {
			if (!Files.exists(rootLocation))
				Files.createDirectory(rootLocation);
		} catch (IOException e) {
			throw new StorageException("Could not initialize storage", e);
		}
	}
}
