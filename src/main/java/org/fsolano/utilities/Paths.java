package org.fsolano.utilities;

import java.io.File;

public class Paths {

    public static final String SEP = File.separator;
    public static final String ROOT_DIR = System.getProperty("user.dir");
    public static final String TEST_OUTPUT_IMAGES_PATH = SEP + "src" + SEP + "testOutput" + SEP + "FailedTestsImages" + SEP;
    public static final String TEST_OUTPUT_PATH = SEP + "src" + SEP + "testOutput" + SEP;

    public static final String OUTPUT_DIRECTORY = Paths.ROOT_DIR + Paths.TEST_OUTPUT_PATH;
    public static final String OUTPUT_FAILED_IMAGES_PATH = Paths.ROOT_DIR + TEST_OUTPUT_IMAGES_PATH;
}
