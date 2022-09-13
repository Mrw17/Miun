package dt066g.assignments.assignment2.task1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Daniel Westerlund
 * Class that will list files/sub directories in a directory
 */
public class Directory implements DirectoryListing{
    File file;

    /**
     * Default constructor that sets file to home directory
     */
    public Directory(){
        file = new File(System.getProperty("user.home"));
    }

    /**
     * Constructor that checks if argument is a directory and
     * if so it updates File
     * @param directory to be checked
     */
    public Directory(File directory){
        file = directory;
        if(!file.isDirectory())
            directory = new File(System.getProperty("user.home"));
    }

    /**
     * Constructor that checks if argument is path to a directory
     * and if so it updates File
     * @param directory path to be checked
     */
    public Directory(String directory){
        file = new File(directory);
        if(!file.isDirectory())
            file = new File(System.getProperty("user.home"));
    }


    /**
     *
     * @param dir path to the directory
     */
    @Override
    public void setDirectory(String dir) {
        if(new File(dir).isDirectory())
            file = new File(dir);
    }

    /**
     * Updates file if it a directory
     * @param dir path to the directory
     */
    @Override
    public void setDirectory(File dir) {
        if(dir.isDirectory())
            file = dir;
    }

    /**
     * Gets all files in a directory
     * @return File[] with all the files in the directory
     */
    @Override
    public File[] getFiles() {
        //Array that keeps track of all files
        File[] filesOutput = new File[file.list().length];
        String[] fileList = file.list();
        int j = 0;

        //Loops through and only saves files to the array
        for (String str : fileList) {
            File newFile = new File(file.getAbsolutePath() + File.separator + str);
            if (newFile.isFile()) {
                filesOutput[j] = newFile;
                j++;
            }
        }
        return filesOutput;
    }

    /**
     * Gets all sub directories in a directory
     * @return File[] with all sub directories
     */
    @Override
    public File[] getDirectories() {
        File[] directoriesOutput = new File[file.list().length];
        String[] directoriesList = file.list();
        int j = 0;

        //Loops through and only saves directories to the array
        for (String str : directoriesList) {
            File newDirectory = new File(file.getAbsolutePath() + File.separator + str);
            if (newDirectory.isDirectory()) {
                directoriesOutput[j] = newDirectory;
                j++;
            }
        }
        return directoriesOutput;
    }

    /**
     * Will print out sub directories and files in a directory
     */
    @Override
    public void list() {
        if(file == null)
            return;

        File[] dirs = getDirectories();
        File[] files = getFiles();
        printDirectories(dirs);
        printFiles(files);
    }

    /**
     * Print out all files in a directory
     * @param dirs contains all the Files that should be printed
     */
    private void printFiles(File[] dirs) {
        try{
            for (File dir : dirs) {
                if (dir != null) {
                    String dirName = setStringSize(dir.getName(), 10);
                    String size = getFileSize(dir.toPath());
                    String modifiedDate = getModifiedDate(dir.toPath());
                    System.out.printf("%s \t\t %s \t\t %s%n", dirName, size, modifiedDate);
                }
            }
        }
        catch (Exception e){
            System.out.println("Exception: " + e.toString());
        }
    }

    /**
     * Will get file size in kB
     * @param path to the file
     * @return string with file size in kB, ex: 524 kB
     * @throws IOException if file could not be found etc
     */
    private String getFileSize(Path path) throws IOException {
        BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
        String output = (Long.toString(attr.size() / 1000));
        output = String.format("%1$"+10+ "s", output + " kB");
        return output;
    }

    /**
     * Print all directories, max length of name is 10.
     * ex: Documents        folder      2021-05-01 12:00
     * @param dirs File[] with all directories
     */
    private void printDirectories(File[] dirs) {
        try{
            for (File dir : dirs) {
                if (dir != null) {
                    String dirName = setStringSize(dir.getName(), 10);
                    String type = setStringSize("folder", 10);
                    String modifiedDate = getModifiedDate(dir.toPath());
                    System.out.printf("%s \t\t %s \t\t %s%n", dirName, type, modifiedDate);
                }
            }
        }
        catch (Exception e){
            System.out.println("Exception: " + e.toString());
        }
    }

    /**
     * Gets date when a file was last modified
     * @param path to the file
     * @return string with last modified date in form: yyyy-MM-dd HH.mm
     * @throws IOException if file could not be found etc
     */
    private String getModifiedDate(Path path) throws IOException {
        BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
        FileTime createDate = attr.lastModifiedTime();
        Date newDate = new Date(createDate.toMillis());
        String pattern = "yyyy-MM-dd HH:mm";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(newDate);
    }

    /**
     * Will set string size to a specified length
     * @param string to be fixed
     * @param length length
     * @return string with new length
     */
    private String setStringSize(String string, int length){
        if(string.length() > 10)
            string =  string.substring(0, length);

        else
            string = String.format("%1$"+length+ "s", string);

        return string;
    }

    /**
     * Prints out absolute path file
     * @return the absolute path to file
     */
    @Override
    public String toString(){
        return file.getAbsolutePath();
    }

}

