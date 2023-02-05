package com.biksue.phonecentral_jdbc_sockets.model.util.files;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Loader {
    /**
     * Devuelve la linea de aparicion de un string pasado por parametro en un archivo del que se pasa la direccion
     * @param path
     * @param search
     * @return
     * @throws IOException
     */
    public static int containerLine(String path, String search)throws IOException{
        try(BufferedReader reader=new BufferedReader(new FileReader(path))){
            List<String> arr=reader.lines().toList();
            for(String s:arr){
                if(s.contains(search)){
                    return arr.indexOf(s);
                }
            }
            return -1;
        }
    }
    /**
     * Lista de archivos del tipo carpeta relativos a las carpetas contenidas en una
     * direccion
     * expecificada
     *
     * @param path
     */
    public static ArrayList<File> directoryFileList(String path) throws IOException {
        File file = new File(path);
        ArrayList<File> files = new ArrayList<>();
        for (File f : file.listFiles(ToolsConstants.directories)) {
            files.add(f);
        }
        return files;
    }

    /**
     * Lista de archivos del tipo archivo relativos a las carpetas contenidas en una
     * direccion
     * expecificada
     *
     * @param path
     * @throws IOException
     */
    public static ArrayList<File> fileFileList(String path) throws IOException {
        File file = new File(path);
        ArrayList<File> files = new ArrayList<>();
        for (File f : file.listFiles(ToolsConstants.files)) {
            files.add(f);
        }
        return files;
    }

    /**
     * Lista de archivos relativos a las carpetas contenidas en una direccion
     * expecificada
     *
     * @param path
     * @throws IOException
     */
    public static ArrayList<File> directoryAndFilesFileList(String path) throws IOException {
        File file = new File(path);
        ArrayList<File> files = new ArrayList<>();
        for (File f : file.listFiles()) {
            files.add(f);
        }
        return files;
    }

    /**
     * Lista las direcciones relativas a las carpetas contenidas en una direccion
     * expecificada
     *
     * @param path
     * @throws IOException
     */
    public static ArrayList<String> directoryDirectoryList(String path) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        for (File f : directoryFileList(path)) {
            list.add(f.getPath());
        }
        return list;
    }

    /**
     * Lista las direcciones relativas a los archivos contenidas en una direccion
     * expecificada
     *
     * @param path
     * @throws IOException
     */
    public static ArrayList<String> directoryAList(String path) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        for (File f : fileFileList(path)) {
            list.add(f.getPath());
        }
        return list;
    }

    /**
     * Carga un archivo encontrado en la direccion expecificada en un solo String
     *
     * @param path - direccion del archivo a cargar
     * @return String
     * @throws IOException
     */
    public static String loadFileAsAString(String path) throws IOException {
        String content = "";
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line;
        int i = 1;
        while ((line = reader.readLine()) != null) {
            if(line!="\n"){
                if (i != 0) {
                    content += line;
                    i--;
                } else {
                    content += "\n" + line;
                }
            }
        }
        reader.close();
        return content;
    }

    public static String loadFileAsAStringInputStream(String path) throws IOException {
        String content = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        String line;
        int i = 1;
        while ((line = reader.readLine()) != null) {
            if(line!="\n"){
                if (i != 0) {
                    content += line;
                    i--;
                } else {
                    content += "\n" + line;
                }
            }
        }
        reader.close();
        return content;
    }


    /**
     * Carga un archivo encontrado en la direccion expecificada en un array de
     * Strings
     *
     * @param path - direccion del archivo a cargar
     * @return String - con el contenido del archivo
     * @throws IOException
     */
    public static ArrayList<String> loadFileAsAStringArr(String path) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line;
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        return list;
    }

    /**
     * Carga un archivo de la direccion expecificada en un array de bytes
     *
     * @param path - direccion del archivo a cargar
     * @return byte[] - con todos los bytes contenidos en el archivo
     * @throws IOException
     * @throws OutOfMemoryError
     */
    public static byte[] loadFileAsByteArr(String path) throws IOException, OutOfMemoryError {
        return loadBytesSecuence(path, 0, new File(path).length() + 1);
    }

    /**
     * Carga el primer byte del archivo encontrado en la direccion expecificada
     *
     * @param path - direccion del archivo a cargar
     * @return byte - el primer byte del archivo
     * @throws IOException
     */
    public static byte loadByte(String path) throws IOException {
        byte content;
        BufferedInputStream inputBytes = new BufferedInputStream(new FileInputStream(path));
        content = (byte) inputBytes.read();
        inputBytes.close();
        return content;
    }

    /**
     * Carga una cantidad expecifica de bytes (indicada en length) desde el
     * indice de inicio (este se incluye en la carga)
     *
     * @param path        - direccion del archivo a cargar
     * @param begginIndex - indice de inicio
     * @param length      - cantidad de bytes
     * @return byte[]
     * @throws IOException
     * @throws OutOfMemoryError
     */
    public static byte[] loadBytesSecuence(String path, long begginIndex, long length)
            throws IOException, OutOfMemoryError {
        if (length > Integer.MAX_VALUE)
            throw new OutOfMemoryError("La cantidad de bytes se pasa del maximo valor posible que puede tomar un int");
        byte[] content;
        BufferedInputStream inputBytes = new BufferedInputStream(new FileInputStream(path));
        inputBytes.skipNBytes(begginIndex);
        content = inputBytes.readNBytes((int) length);
        inputBytes.close();
        return content;
    }

    /**
     * Carga un objeto en el archivo que se encuentra en la direccion pasada como
     * parametro
     *
     * @param path
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Object loadObject(String path) throws IOException, ClassNotFoundException {
        InputStream out = new FileInputStream(path);
        try (ObjectInputStream loader = new ObjectInputStream(out)) {
            return loader.readObject();
        }
    }

}
