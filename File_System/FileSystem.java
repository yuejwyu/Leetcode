package File_System;

import javax.swing.border.EmptyBorder;
import java.util.ArrayList;
import java.util.List;

public class FileSystem {
    private final Directory root;

    public FileSystem() {
        root = new Directory("/", null);
    }

    private List<Entry> resolve(String path) { // no throws ????
        assert path.startsWith("/");
        String[] components = path.substring(1).split("/");
        List<Entry> entries = new ArrayList<>();
        entries.add(root);

        Entry entry = root;
        for (String component : components) {
            if (entry == null || !(entry instanceof Directory)) {
                throw new IllegalArgumentException("invalid path: " + path);
            }
            if (!component.isEmpty()) {
                entry = ((Directory) entry).getChild(component);
                entries.add(entry);
            }
        }
        return entries;
    }

    public void mkdir(String path) {
        List<Entry> entries = resolve(path);
        if (entries.get(entries.size() - 1) != null) {
            throw new IllegalArgumentException("Directory already exists: " + path);
        }
        String[] components = path.split("/");
        final String dirName = components[components.length - 1];
        final Directory parent = (Directory) entries.get(entries.size() - 2);
        Directory newDir = new Directory(dirName, parent);
        parent.addEntry(newDir);
    }

    public void createFile(String path) {
        assert !path.endsWith("/");
        List<Entry> entries = resolve(path);
        if (entries.get(entries.size() - 1) != null) {
            throw new IllegalArgumentException("File already exists: " + path);
        }
        final String fileName = path.substring(path.lastIndexOf("/") + 1);
        final Directory parent = (Directory) entries.get(entries.size() - 2);
        File file = new File(fileName, parent, 0);
        parent.addEntry(file);
    }

    public void delete(String path) {
        List<Entry> entries = resolve(path);
        if (entries.get(entries.size() - 1) == null) return;
        final Directory target = (Directory) entries.get(entries.size() - 1);
        final Directory parent = (Directory) entries.get(entries.size() - 2);
        parent.deleteEntry(target);
    }

    public List<Entry> list(String path) {
        List<Entry> entries = resolve(path);
        if (entries.get(entries.size() - 1) == null ||
                !(entries.get(entries.size() - 1) instanceof Directory)) {
            return new ArrayList<>();
        }
        return ((Directory) entries.get(entries.size() - 1)).getContents();
    }

    public int count() {
        return root.numberOfFiles();
    }

    public static void main(String[] args) {
        FileSystem fs = new FileSystem();
        fs.mkdir("/foo");
        fs.mkdir("/foo/bar");
        fs.createFile("/bar");
        fs.createFile("/foo/foo");
        fs.createFile("/foo/bar/bar");
        fs.list("/");
        System.out.println(fs.list("/").size());
    }
}
