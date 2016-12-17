package org.ircenter.serv.dataserver.images.impl;

import org.ircenter.filestorage.BadFileException;
import org.ircenter.filestorage.FileStorage;
import org.ircenter.filestorage.NoPartException;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * User: Seledkov Kostyantyn
 * Date: 15.05.12
 * Time: 2:14
 */
public class MockStorage extends FileStorage {

    private boolean throwFileNotFound;
    public MockStorage(boolean throwFileNotFound) {
        super(false, null);
        this.throwFileNotFound = throwFileNotFound;
    }

    @Override
    public boolean checkFile(long id) throws Exception {
        return true;
    }

    @Override
    public String getDirectoryName(long id) {
        return "none";
    }

    @Override
    public byte[] getFile(long id, int index) throws FileNotFoundException, BadFileException, NoPartException {
        if (throwFileNotFound) {
            throw new FileNotFoundException();
        }
        return null;
    }

    @Override
    public String getFileName(long id) {
        return "none";
    }

    @Override
    public byte[][] getFullFile(long id) throws FileNotFoundException, BadFileException, NoPartException {
        if (throwFileNotFound) {
            throw new FileNotFoundException();
        }
        return null;
    }

    @Override
    public boolean isFileExist(long id) {
        return false;
    }

    @Override
    public boolean removeFile(long id) {
        return true;
    }

    @Override
    public boolean saveFile(long id, byte[] bytes) {
        return true;
    }

    @Override
    public boolean saveFile(long id, List<byte[]> parts) {
        return true;
    }
}
