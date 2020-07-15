public enum FileFormat {

    ONE(1), TWO(2);

    private final int fileFormat;

    FileFormat(int fileFormat) {
        this.fileFormat = fileFormat;
    }

    public int getFileFormat() {
        return this.fileFormat;
    }

}
