package com.google.i18n.phonenumbers;

/**
 * Unit tests for MultiFileMetadataSourceImpl.java
 */
public class MultiFileMetadataSourceTest extends TestMetadataTestCase  {

  protected final MultiFileMetadataSourceImpl multipleMetadataSource;

  public MultiFileMetadataSourceTest() {
    multipleMetadataSource = new MultiFileMetadataSourceImpl(TEST_META_DATA_FILE_PREFIX,
          PhoneNumberUtil.DEFAULT_METADATA_LOADER);
  }

  public void testMissingMetadataFileThrowsRuntimeException() {
    // In normal usage we should never get a state where we are asking to load metadata that doesn't
    // exist. However if the library is packaged incorrectly in the jar, this could happen and the
    // best we can do is make sure the exception has the file name in it.
    try {
      multipleMetadataSource.loadMetadataFromFile(
          "no/such/file", "XX", -1, PhoneNumberUtil.DEFAULT_METADATA_LOADER);
      fail("expected exception");
    } catch (RuntimeException e) {
      assertTrue("Unexpected error: " + e, e.toString().contains("no/such/file_XX"));
    }
    try {
      multipleMetadataSource.loadMetadataFromFile("no/such/file", PhoneNumberUtil.REGION_CODE_FOR_NON_GEO_ENTITY,
          123, PhoneNumberUtil.DEFAULT_METADATA_LOADER);
      fail("expected exception");
    } catch (RuntimeException e) {
      assertTrue("Unexpected error: " + e, e.getMessage().contains("no/such/file_123"));
    }
  }

}
