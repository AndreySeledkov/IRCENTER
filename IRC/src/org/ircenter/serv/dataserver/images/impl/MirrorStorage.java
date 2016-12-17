package org.ircenter.serv.dataserver.images.impl;

import org.ircenter.filestorage.BadFileException;
import org.ircenter.filestorage.FileStorage;
import org.ircenter.filestorage.NoPartException;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * User: Seledkov Kostyantyn
 * Date: 15.05.12
 * Time: 2:13
 */
public class MirrorStorage {
    private FileStorage storage1;
    private FileStorage storage2;

    public MirrorStorage(FileStorage storage1, FileStorage storage2) {
        this.storage1 = storage1;
        this.storage2 = storage2;
    }

    public FileStorage getStorage1() {
        return storage1;
    }

    public FileStorage getStorage2() {
        return storage2;
    }

    public boolean checkFile(long id) throws Exception {
        boolean f1 = storage1.checkFile(id);
        boolean f2 = storage1.checkFile(id);
        return f1 && f2;
    }

    public String getFileName(long id) {
        return storage1.getFileName(id) + " | " + storage2.getFileName(id);
    }

    public Object getFileLock(long id) {
        return storage1.getFileLock(id);
    }

    public byte[] getFile(long id, int index) throws FileNotFoundException, BadFileException, NoPartException {
        try {
            return storage1.getFile(id, index);
        } catch (FileNotFoundException ex) {
            return storage2.getFile(id, index);
        }
    }

    public byte[][] getFullFile(long id) throws FileNotFoundException, BadFileException, NoPartException {
        try {
            return storage1.getFullFile(id);
        } catch (FileNotFoundException ex) {
            return storage2.getFullFile(id);
        }
    }

    public boolean isFileExist(long id) {
        return storage1.isFileExist(id) || storage2.isFileExist(id);
    }

    public boolean removeFile(long id) {
        boolean f1 = storage1.removeFile(id);
        boolean f2 = storage1.removeFile(id);
        return f1 && f2;
    }

    public boolean saveFile(long id, List<byte[]> parts) {
        boolean f1 = storage1.saveFile(id, parts);
        boolean f2 = storage2.saveFile(id, parts);
        return f1 && f2;
    }

    public boolean saveFile(long id, byte[] data) {
        boolean f1 = storage1.saveFile(id, data);
        boolean f2 = storage2.saveFile(id, data);
        return f1 && f2;
    }
}
