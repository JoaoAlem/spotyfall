package com.player.spotyfall.modules;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {
    /** Check if a string is null, blank or empty
     * returns false is empty
     *
     */
    public static boolean validateString(String string){
        if(string == null || string.equals("null") || string.isEmpty() || string.trim().isEmpty())
            return false;

        return true;
    }

    /** generate a unique file name for saving
     *
     */
    private static String _generateUniqueFileName(String originalFileName) throws NoSuchAlgorithmException {
        String timeStamp = String.valueOf(System.currentTimeMillis());
        String hash = generateHash(originalFileName);

        String fileExtension = "";
        int dotIndex = originalFileName.lastIndexOf(".");
        if (dotIndex >= 0 && dotIndex < originalFileName.length() - 1) {
            fileExtension = originalFileName.substring(dotIndex + 1);
        }

        return timeStamp + "_" + hash + "." + fileExtension;
    }

    /** Generate a hash for the unique file name
     *
     */
    private static String generateHash(String input) throws NoSuchAlgorithmException {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashText = no.toString(16);
            while (hashText.length() < 32) {
                hashText = "0" + hashText;
            }
            return hashText;
        } catch (NoSuchAlgorithmException exception){
            throw exception;
        }
    }

    /** Method that saves a file
     *
     * @param req the request
     * @param path the path
     * @param key the key for get in request
     * @return String
     * @throws ServletException erro in servlet
     * @throws IOException error of Input Output Read or write
     * @throws NoSuchAlgorithmException no available algorithm
     */
    public static String saveFile(HttpServletRequest req, String path, String key) throws ServletException, IOException, NoSuchAlgorithmException {
        File saveDir = new File(path);
        if (!saveDir.exists()) {
            saveDir.mkdirs();
        }

        Part filePart = req.getPart(key);
        if (filePart != null && filePart.getSize() > 0) {
            String fileName = _generateUniqueFileName(filePart.getSubmittedFileName());
            String filePath = path + File.separator + fileName;

            try (InputStream inputStream = filePart.getInputStream();
                 FileOutputStream outputStream = new FileOutputStream(filePath)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }

            return fileName;
        }

        return "placeholder.webp";
    }
}
