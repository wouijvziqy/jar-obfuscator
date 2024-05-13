package me.n1ar4.jar.obfuscator.loader;

import me.n1ar4.jar.obfuscator.Const;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CustomClassLoader extends ClassLoader {
    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] b = loadClassData(name);
        return defineClass(name, b, 0, b.length);
    }

    private byte[] loadClassData(String name) {
        String classPath = name.replace('.', '/') + ".class";
        Path path = Paths.get(Const.TEMP_DIR).resolve(classPath);
        try {
            return Files.readAllBytes(path);
        } catch (Exception ignored) {
            return null;
        }
    }
}
