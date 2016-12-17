package org.ircenter.server.web.media;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
public class ImgController {

    protected final Log logger = LogFactory.getLog(getClass());

    @RequestMapping(value = "{userId}/{imgName}.{ext}", method = RequestMethod.GET)
    public void getImg(@PathVariable Long userId,
                       @PathVariable String imgName, @PathVariable String ext, HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpg");
        String path = System.getProperty("catalina.home") + File.separator + "webapps" + File.separator + "ROOT" + File.separator + "img" + File.separator + userId + File.separator + imgName + "." + ext;
        File file = new File(path);
        try {
            ServletOutputStream responseOutputStream = response.getOutputStream();
            responseOutputStream.write(getBytesFromFile(file));
            responseOutputStream.flush();
            responseOutputStream.close();
        }
//        catch (ClientAbortException e) {
////            logger.error("error reading file" + path, e);
//        }
        catch (Exception e) {
            logger.error("error reading file" + path, e);
        }
    }

    // Returns the contents of the file in a byte array.
    private static byte[] getBytesFromFile(File file) throws IOException {
        InputStream inputStream = new FileInputStream(file);
        BufferedInputStream is = new BufferedInputStream(inputStream);
        // Get the size of the file
        long length = file.length();
        // You cannot create an array using a long type.
        // It needs to be an int type.
        // Before converting to an int type, check
        // to ensure that file is not larger than Integer.MAX_VALUE.
        if (length > Integer.MAX_VALUE) {
            // File is too large
        }
        // Create the byte array to hold the data
        byte[] bytes = new byte[(int) length];
        // Read in the bytes
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
                && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }
        // Ensure all the bytes have been read in
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file " + file.getName());
        }
        // Close the input stream and return bytes
        is.close();
        return bytes;
    }
}
