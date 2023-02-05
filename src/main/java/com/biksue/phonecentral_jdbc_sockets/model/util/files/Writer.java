package com.biksue.phonecentral_jdbc_sockets.model.util.files;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Para guardar informacion en archivos Se definen identificadores para los
 * metodos que estaran en los nombres de los mismos: over -> ( para indicar que
 * el metodo sobreescribe el archivo en su totalidad) apnd -> ( para indicar que
 * el metodo agrega datos al archivo) ovap -> ( para indicar que el metodo
 * sobreescribe informacion del archivo con otra)
 *
 * @author Vìctor
 */
public class Writer {

    /**
     * Crea un archivo en el directorio expecificado con la extencion expecificada
     * (si no existe el directorio lo crea con todas las carpetas necesarias)
     * Lanza excepcion si no se poseen los permisos necesarios para la creacion
     *
     * @param root
     * @throws IOException
     */
    public static void createRoot(String root) throws IOException {
        new File(root).mkdirs();
    }

    /**
     * Borra el archivo encontrado en la direccion pasada por parametro
     *
     * @param path
     * @throws IOException
     */
    public static void delete(String path) throws IOException {
        File f = new File(path);
        if (ToolsConstants.directories.accept(f)) {
            for (File fi : f.listFiles()) {
                fi.delete();
            }
        }
        f.delete();
    }

    /**
     * Sobreescribe el archivo en su totalidad con un String pasado por
     * parametro
     *
     * @param path - direccion del archivo a sobreescribir
     * @param newString    - String de sobreescritura
     * @throws IOException
     */
    public static void overString(String path, String newString) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write(newString);
        }
    }

    /**
     * Agrega un salto de linea al final del archivo
     *
     * @param path - direccion del archivo
     * @throws IOException
     */
    public static void apdnNewLine(String path) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
            writer.newLine();
        }
    }

    /**
     * Agrega una secuencia de caracteres del tipo String (directamente) pasado
     * por parametro
     *
     * @param path - direccion del archivo
     * @param newString    - String a agregar
     * @throws IOException
     */
    public static void apdnString(String path, String newString) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
            writer.append(newString);
        }
    }

    /**
     * Agrega una secuencia de caracteres del tipo String (agrega un salto de
     * linea despues) pasado por parametro
     *
     * @param path - direccion del archivo
     * @param newString    - String a agregar
     * @throws IOException
     */
    public static void apdnStringLn(String path, String newString) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
            writer.append(newString);
            writer.newLine();
        }
    }

    /**
     * Agrega una secuencia de caracteres del tipo String (directamente)(agrega
     * un char antes) pasado por parametro
     *
     * @param path - direccion del archivo
     * @param newString    - String a agregar
     * @param c    - char separador
     * @throws IOException
     */
    public static void apdnStringChar(String path, String newString, char c) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
            writer.append(c + newString);
        }
    }

    /**
     * Agrega una secuencia de caracteres del tipo String (directamente)(agrega
     * un String antes) pasado por parametro
     *
     * @param path - direccion del archivo
     * @param newString    - String a agregar
     * @param c    - String separador
     * @throws IOException
     */
    public static void apdnStringChar(String path, String newString, String c) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
            writer.append(c + newString);
        }
    }

    /**
     * Agrega una secuencia de caracteres del tipo String (agrega un salto de
     * linea antes)(agrega un char despues) pasado por parametro
     *
     * @param path - direccion del archivo
     * @param newString    - String a agregar
     * @param c    - char separador
     * @throws IOException
     */
    public static void apdnStringCharLn(String path, String newString, char c) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
            writer.append(c + newString);
            writer.newLine();
        }
    }

    /**
     * Agrega una secuencia de caracteres del tipo String (agrega un String
     * antes)(agrega un salto de
     * linea despues) pasado por parametro
     *
     * @param path - direccion del archivo
     * @param newString    - String a agregar
     * @param c    - char separador
     * @throws IOException
     */
    public static void apdnStringCharLn(String path, String newString, String c) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
            writer.append(c + newString);
            writer.newLine();
        }
    }

    /**
     * Remplaza todas las apariciones de una secuencia de caracteres del tipo
     * String con otra, ambas pasadas por parametro
     *
     * @param path   - direccion del archivo
     * @param newString      - String a agregar
     * @param oldString - String a remplazar
     * @throws IOException
     */
    public static void ovapAllString(String path, String newString, String oldString) throws IOException {
        String loaded = Loader.loadFileAsAString(path);
        String content = loaded.replaceAll(oldString, newString);
        overString(path, content);
    }

    /**
     * Remplaza la primera aparicion de una secuencia de caracteres del tipo
     * String con otra, ambas pasadas por parametro
     *
     * @param path   - direccion del archivo
     * @param newString      - String a agregar
     * @param oldString - String a remplazar
     * @throws IOException
     */
    public static void ovapString(String path, String newString, String oldString) throws IOException {
        String loaded = Loader.loadFileAsAString(path);
        String content = loaded.replace(oldString, newString);
        overString(path, content);
    }

    /**
     * Remplaza la aparicion numero n de una secuencia de caracteres del tipo
     * String con otra, ambas pasadas por parametro
     *
     * @param path   - direccion del archivo
     * @param newString      - String a agregar
     * @param oldString - String a remplazar
     * @param n      - n aparicion del String
     * @throws IOException
     */
    public static void ovapNString(String path, String newString, String oldString, int n) throws IOException {
        Writer w = new Writer();
        String loaded = Loader.loadFileAsAString(path);
        ArrayList<Integer> ns = w.ns(oldString, loaded);
        String reference = loaded.substring(ns.get(n - 1)).replaceFirst(oldString, newString);
        loaded = loaded.substring(0, ns.get(n - 1));
        String content = loaded + reference;
        overString(path, content);
    }

    /**
     * Remplaza un String directamente despues de la aparicion numero n de una
     * secuencia de caracteres del tipo String, ambas pasadas por parametro
     *
     * @param path   - direccion del archivo
     * @param newString      - String a agregar
     * @param oldString - String a remplazar
     * @param n      - n aparicion del String
     * @throws IOException
     */
    public static void ovapNextString(String path, String newString, String oldString, int n) throws IOException {
        Writer w = new Writer();
        String loaded = Loader.loadFileAsAString(path);
        ArrayList<Integer> ns = w.ns(oldString, loaded);
        int SLength = newString.length();
        String ref;
        ref = loaded.substring(ns.get(n - 1)).substring(0, SLength);
        String reference = loaded.substring(ns.get(n - 1)).replaceFirst(ref, newString);
        loaded = loaded.substring(0, ns.get(n - 1));
        String content = loaded + reference;
        overString(path, content);
    }

    /**
     * Sobreescribe una linea en la que se encuentre la aparicion numero n de un string(pasado por parametro) con otro setring
     * @param path
     * @param newString
     * @param oldString
     * @param n
     */
    public static void ovapLineString(String path, String newString, String oldString, int n) throws IOException {
        try(BufferedReader reader=new BufferedReader(new FileReader(new File(path)))){
            String aux="";
            int aux1=0;
            int i=0;
            boolean cont=false;
            List<String> arr=reader.lines().toList();
            for(String s:arr){
                if(s.contains(oldString)){
                    aux1++;
                    if(aux1==n){
                        s=newString+arr.get(i);
                        cont=true;
                    }
                    if(cont){
                        cont=false;
                        continue;
                    }
                }
                i++;
                if(i==arr.size()-1){
                    aux+=s;
                }else{
                    aux+=s+"\n";
                }
            }
            overString(path,aux);
        }
    }

    /**
     * Guarda un objeto pasado por parametro en el archivo que
     * se encuentra en la direccion pasada como parametro
     *
     * @param obj
     * @param path
     * @throws IOException
     */
    public static void writeObject(Object obj, String path) throws IOException {
        OutputStream out = new FileOutputStream(path);
        try (ObjectOutputStream writer = new ObjectOutputStream(out)) {
            writer.writeObject(obj);
        }
    }

    /**
     * Retorna un ArrayList con todos los indices de las apariciones de la
     * cadena buscada
     *
     * @param search
     * @param loaded
     * @return
     * @throws IOException
     */
    private ArrayList<Integer> ns(String search, String loaded) throws IOException {
        ArrayList<Integer> ns = new ArrayList<>();
        int ref = 0;
        while (ref != -1) {
            if (ref == 0) {
                ns.add(loaded.indexOf(search));
                ref++;
            } else {
                ref = loaded.indexOf(search, ns.get(ns.size() - 1) + 1);
                ns.add(ref);
            }
        }
        return ns;
    }

    /**
     * Borra una carpeta o archivo encontrado en una direccion dada
     * @param file
     */
    public static void deleteDirectory(File file){
        if(file.isFile()){
            file.delete();
            return;
        }
        if(file.isDirectory()&&!Arrays.stream(file.list()).toList().isEmpty()){
            for(File aux:file.listFiles()){
                deleteDirectory(aux);
            }
        }
        if(file.isDirectory()&& Arrays.stream(file.list()).toList().isEmpty()){
            file.delete();
        }
    }

/*
  */
}