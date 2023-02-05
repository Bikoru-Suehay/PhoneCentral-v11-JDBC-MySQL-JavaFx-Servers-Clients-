package com.biksue.phonecentral_jdbc_sockets.model.util.files;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Comparator;

public class ToolsConstants {
    public static final Comparator NATURAL_ORDER = Comparator.naturalOrder();
    public static FileFilter directories = new FileFilter() {

        @Override
        public boolean accept(File pathname) {
            return pathname.isDirectory();
        }

    };

    public static FileFilter files = new FileFilter() {
        @Override
        public boolean accept(File pathname) {
            return pathname.isFile();
        }
    };
}
