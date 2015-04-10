/*******************************************************************************
 * Copyright (c) 2000, 2015 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.swt.tests.junit;


import static org.eclipse.swt.tests.junit.SwtTestUtil.assertSWTProblem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageDataProvider;
import org.eclipse.swt.graphics.ImageFileNameProvider;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.internal.Compatibility;
import org.eclipse.swt.widgets.Display;

import junit.framework.TestCase;

/**
 * Automated Test Suite for class org.eclipse.swt.graphics.Image
 *
 * @see org.eclipse.swt.graphics.Image
 */
public class Test_org_eclipse_swt_graphics_Image extends TestCase {
ImageFileNameProvider imageFileNameProvider = new ImageFileNameProvider() {
	public String getImagePath(int zoom) {
		String fileName;
		switch (zoom) {
		case 100:
			fileName = "collapseall.png";
			break;
		case 150: 
			fileName = "collapseall@1.5x.png";
			break;
		case 200: 
			fileName = "collapseall@2x.png";
			break;
		default:
			return null;
		}
		return getPath(fileName);
	}
};
ImageDataProvider imageDataProvider = new ImageDataProvider() {
	public ImageData getImageData(int zoom) {
		String fileName;
		switch (zoom) {
		case 100:
			fileName = "collapseall.png";
			break;
		case 150: 
			fileName = "collapseall@1.5x.png";
			break;
		case 200: 
			fileName = "collapseall@2x.png";
			break;
		default:
			return null;
		}
		return new ImageData(getPath(fileName));
	}
};
@Override
protected void setUp() {
	display = Display.getDefault();
}

public void test_ConstructorLorg_eclipse_swt_graphics_DeviceII() {
	Image image;
	try {
		image = new Image(display, -1, 10);
		image.dispose();
		fail("No exception thrown for width <= 0");
	} catch (IllegalArgumentException e) {
		assertSWTProblem("Incorrect exception thrown for width <= 0", SWT.ERROR_INVALID_ARGUMENT, e);
	}

	try {
		image = new Image(display, 0, 10);
		image.dispose();
		fail("No exception thrown for width <= 0");
	} catch (IllegalArgumentException e) {
		assertSWTProblem("Incorrect exception thrown for width <= 0", SWT.ERROR_INVALID_ARGUMENT, e);
	}

	try {
		image = new Image(display, 10, -20);
		image.dispose();
		fail("No exception thrown for height <= 0");
	} catch (IllegalArgumentException e) {
		assertSWTProblem("Incorrect exception thrown for height <= 0", SWT.ERROR_INVALID_ARGUMENT, e);
	}

	try {
		image = new Image(display, 10, 0);
		image.dispose();
		fail("No exception thrown for height <= 0");
	} catch (IllegalArgumentException e) {
		assertSWTProblem("Incorrect exception thrown for height <= 0", SWT.ERROR_INVALID_ARGUMENT, e);
	}

	image = new Image(null, 10, 10);
	image.dispose();

	image = new Image(display, 10, 10);
	image.dispose();
		
}

public void test_ConstructorLorg_eclipse_swt_graphics_DeviceLorg_eclipse_swt_graphics_Rectangle() {
	Image image;
	Rectangle bounds = null;

	try {
		image = new Image(display, bounds);
		image.dispose();
		fail("No exception thrown for rectangle == null");
	} catch (IllegalArgumentException e) {
		assertSWTProblem("Incorrect exception thrown for rectangle == null", SWT.ERROR_NULL_ARGUMENT, e);
	}

	bounds = new Rectangle(0, 0, -1, 10);
	try {
		image = new Image(display, bounds);
		image.dispose();
		fail("No exception thrown for width < 0");
	} catch (IllegalArgumentException e) {
		assertSWTProblem("Incorrect exception thrown for width < 0", SWT.ERROR_INVALID_ARGUMENT, e);
	}

	bounds = new Rectangle(0, 0, 0, 10);
	try {
		image = new Image(display, bounds);
		image.dispose();
		fail("No exception thrown for width == 0");
	} catch (IllegalArgumentException e) {
		assertSWTProblem("Incorrect exception thrown for width == 0", SWT.ERROR_INVALID_ARGUMENT, e);
	}

	bounds = new Rectangle(0, 0, 10, -1);
	try {
		image = new Image(display, bounds);
		image.dispose();
		fail("No exception thrown for height < 0");
	} catch (IllegalArgumentException e) {
		assertSWTProblem("Incorrect exception thrown for height < 0", SWT.ERROR_INVALID_ARGUMENT, e);
	}

	bounds = new Rectangle(0, 0, 10, 0);
	try {
		image = new Image(display, bounds);
		image.dispose();
		fail("No exception thrown for height == 0");
	} catch (IllegalArgumentException e) {
		assertSWTProblem("Incorrect exception thrown for height == 0", SWT.ERROR_INVALID_ARGUMENT, e);
	}

	// valid images
	bounds = new Rectangle(-1, -10, 10, 10);
	image = new Image(display, bounds);
	image.dispose();

	bounds = new Rectangle(0, 0, 10, 10);
	image = new Image(null, bounds);
	image.dispose();
	
	image = new Image(display, bounds);
	image.dispose();
}

public void test_ConstructorLorg_eclipse_swt_graphics_DeviceLorg_eclipse_swt_graphics_ImageData() {
	ImageData data = null;
	Image image = null;
	
	try {
		image = new Image(display, data);
		image.dispose();
		fail("No exception thrown for ImageData == null");
	} catch (IllegalArgumentException e) {
		assertSWTProblem("Incorrect exception thrown for ImageData == null", SWT.ERROR_NULL_ARGUMENT, e);
	}

//	Platform-specific test.  
//	data = new ImageData(10, 10, 1, new PaletteData(0xff0000, 0x00ff00, 0x0000ff));
//	try {
//		image = new Image(display, data);
//		image.dispose();
//		fail("Unsupported color depth");
//	} catch (SWTException e) {
//	}

	data = new ImageData(10, 10, 1, new PaletteData(new RGB[] {new RGB(0, 0, 0)}));
	image = new Image(null, data);
	image.dispose();

	data = new ImageData(10, 10, 1, new PaletteData(new RGB[] {new RGB(0, 0, 0)}));
	image = new Image(display, data);
	image.dispose();
	
	data = new ImageData(10, 10, 8, new PaletteData(0x30, 0x0C, 0x03));
	// set red pixel at x=9, y=9
	data.setPixel(9, 9, 0x30);
	image = new Image(display, data);
	Image gcImage = new Image(display, 10, 10);
	GC gc = new GC(gcImage);
	gc.drawImage(image, 0, 0);
	ImageData gcImageData = gcImage.getImageData();
	int redPixel = gcImageData.getPixel(9, 9);
	assertEquals(":a:", getRealRGB(display.getSystemColor(SWT.COLOR_RED)), gcImageData.palette.getRGB(redPixel));
	gc.dispose();
	gcImage.dispose();
	image.dispose();
}

public void test_ConstructorLorg_eclipse_swt_graphics_DeviceLorg_eclipse_swt_graphics_ImageDataLorg_eclipse_swt_graphics_ImageData() {
	ImageData data = null;
	ImageData data1 = new ImageData(10, 10, 1, new PaletteData(new RGB[] {new RGB(0, 0, 0)}));
	Image image = null;
	
	try {
		image = new Image(display, data, data1);
		image.dispose();
		fail("No exception thrown for ImageData source == null");
	} catch (IllegalArgumentException e) {
		assertSWTProblem("Incorrect exception thrown for ImageData source == null", SWT.ERROR_NULL_ARGUMENT, e);
	}

	data = new ImageData(10, 10, 1, new PaletteData(new RGB[] {new RGB(0, 0, 0)}));
	data1 = null;
	try {
		image = new Image(display, data, data1);
		image.dispose();
		fail("No exception thrown for ImageData mask == null");
	} catch (IllegalArgumentException e) {
		assertSWTProblem("Incorrect exception thrown for ImageData mask == null", SWT.ERROR_NULL_ARGUMENT, e);
	}

	data = new ImageData(10, 10, 1, new PaletteData(new RGB[] {new RGB(0, 0, 0)}));
	data1 = new ImageData(1, 10, 1, new PaletteData(new RGB[] {new RGB(0, 0, 0)}));
	try {
		image = new Image(display, data, data1);
		image.dispose();
		fail("No exception thrown for ImageData source width != ImageData mask width");
	} catch (IllegalArgumentException e) {
		assertSWTProblem("Incorrect exception thrown for ImageData source width != ImageData mask width", SWT.ERROR_INVALID_ARGUMENT, e);
	}

	data = new ImageData(10, 1, 1, new PaletteData(new RGB[] {new RGB(0, 0, 0)}));
	data1 = new ImageData(10, 10, 1, new PaletteData(new RGB[] {new RGB(0, 0, 0)}));
	try {
		image = new Image(display, data, data1);
		image.dispose();
		fail("No exception thrown for ImageData source height != ImageData mask height");
	} catch (IllegalArgumentException e) {
		assertSWTProblem("Incorrect exception thrown for ImageData source height != ImageData mask height", SWT.ERROR_INVALID_ARGUMENT, e);
	}

	data = new ImageData(10, 10, 8, new PaletteData(new RGB[] {new RGB(0, 0, 0)}));
	data1 = new ImageData(10, 10, 8, new PaletteData(new RGB[] {new RGB(0, 0, 0)}));
	image = new Image(display, data, data1); // Image now accepts masks where depth != 1
	image.dispose();

	data = new ImageData(10, 10, 8, new PaletteData(0x30, 0x0C, 0x03));
	// set opaque red pixel at x=9, y=9
	data.setPixel(9, 9, 0x30);
	data1 = new ImageData(10, 10, 1, new PaletteData(new RGB[] {new RGB(0, 0, 0), new RGB(255, 255, 255)}));
	data1.setPixel(9, 9, 1);
	image = new Image(display, data, data1);
	Image gcImage = new Image(display, 10, 10);
	GC gc = new GC(gcImage);
	Color backgroundColor = display.getSystemColor(SWT.COLOR_BLUE); 
	gc.setBackground(backgroundColor);
	gc.fillRectangle(0, 0, 10, 10);
	gc.drawImage(image, 0, 0);
	ImageData gcImageData = gcImage.getImageData();
	int redPixel = gcImageData.getPixel(9, 9);
	assertEquals(":a:", getRealRGB(display.getSystemColor(SWT.COLOR_RED)), gcImageData.palette.getRGB(redPixel));
	int bluePixel = gcImageData.getPixel(0, 0);
	assertEquals(":b:", getRealRGB(backgroundColor), gcImageData.palette.getRGB(bluePixel));
	gc.dispose();
	gcImage.dispose();
	image.dispose();
}

public void test_ConstructorLorg_eclipse_swt_graphics_DeviceLjava_io_InputStream() {
	InputStream stream = null;
	Image image = null;
	try {
		try {
			image = new Image(display, stream);
			image.dispose();
			fail("No exception thrown for InputStream == null");
		} catch (IllegalArgumentException e) {
			assertSWTProblem("Incorrect exception thrown for InputStream == null", SWT.ERROR_NULL_ARGUMENT, e);
		}
		
		stream = SwtTestUtil.class.getResourceAsStream("empty.txt");
		try {
			image = new Image(display, stream);
			image.dispose();
			try {
				stream.close();
			} catch (IOException e) {}
			fail("No exception thrown for invalid InputStream");
		} catch (SWTException e) {
			assertSWTProblem("Incorrect exception thrown for invalid InputStream", SWT.ERROR_UNSUPPORTED_FORMAT, e);
		}

		int numFormats = SwtTestUtil.imageFormats.length;
		String fileName = SwtTestUtil.invalidImageFilenames[0];
		Display[] displays = {display, null};
		for (int j = 0; j < displays.length; j++) {
//			Display tempDisplay = displays[j];
			for (int i=0; i<numFormats; i++) {
				String format = SwtTestUtil.imageFormats[i];
				stream = SwtTestUtil.class.getResourceAsStream(fileName + "." + format);

				try {
					image = new Image(display, stream);
					image.dispose();
					try {
						stream.close();
					} catch (IOException e) {}
					fail("No exception thrown for invalid InputStream");
				} catch (SWTException e) {
// Bug 70167 - Image(Device, InputStream) throws incorrect exception for bad PNG
// remove comment when bug is fixed.
//					assertEquals("Incorrect exception thrown for invalid image InputStream", SWT.ERROR_INVALID_IMAGE, e);
				}
			}
		}

		stream = SwtTestUtil.class.getResourceAsStream(SwtTestUtil.invalidImageFilenames[1]);
		try {
			image = new Image(display, stream);
			image.dispose();
			try {
				stream.close();
			} catch (IOException e) {}
			fail("No exception thrown for invalid InputStream");
		} catch (SWTException e) {
			assertSWTProblem("Incorrect exception thrown for invalid image InputStream", SWT.ERROR_INVALID_IMAGE, e);
		}		

		// create valid images
		for (int j = 0; j < displays.length; j++) {
			Display tempDisplay = displays[j];
			int numFileNames = SwtTestUtil.imageFilenames.length;
			for (int k=0; k<numFileNames; k++) {
				fileName = SwtTestUtil.imageFilenames[k];		
				for (int i=0; i<numFormats; i++) {
					String format = SwtTestUtil.imageFormats[i];
					stream = SwtTestUtil.class.getResourceAsStream(fileName + "." + format);
					image = new Image(tempDisplay, stream);
					image.dispose();
					try {
						stream.close();
					} catch (IOException e) {}
				}
			}
		}
	} finally {
		try {
			stream.close();
		} catch (Exception e) {
		}
	}
}

public void test_ConstructorLorg_eclipse_swt_graphics_DeviceLjava_lang_String() {
	String fileName = null;
	try {
		Image image = new Image(display, fileName);
		image.dispose();
		fail("No exception thrown for file name == null");
	} catch (IllegalArgumentException e) {
		assertSWTProblem("Incorrect exception thrown for file name == null", SWT.ERROR_NULL_ARGUMENT, e);
	}
	try {
		String pathName = "nonexistent.txt";
		Image image = new Image(display, pathName);
		image.dispose();
		fail("No exception thrown for non-existent file name");
	} catch (SWTException e) {
		assertSWTProblem("Incorrect exception thrown for non-existent file name", SWT.ERROR_IO, e);
	}
	// j2se and j2me(cdc) can load from a file name but, j2me(cldc) throws an exception
	if (!isJ2ME()) {
		try {
			String pathName = getPath("empty.txt");
			Image image = new Image(display, pathName);
			image.dispose();
			fail("No exception thrown for invalid file name");
		} catch (SWTException e) {
			assertSWTProblem("Incorrect exception thrown for invalid file name", SWT.ERROR_UNSUPPORTED_FORMAT, e);
		}
	
		int numFormats = SwtTestUtil.imageFormats.length;
		fileName = SwtTestUtil.invalidImageFilenames[0];
		Display[] displays = {display, null};
		for (int j = 0; j < displays.length; j++) {
//			Display tempDisplay = displays[j];
			for (int i=0; i<numFormats; i++) {
				String format = SwtTestUtil.imageFormats[i];
	
				try {
					String pathName = getPath(fileName + "." + format);
					Image image = new Image(display, pathName);
					image.dispose();
					fail("No exception thrown for invalid file name");
				} catch (SWTException e) {
//					 Bug 70167 - Image(Device, InputStream) throws incorrect exception for bad PNG
//					 remove comment when bug is fixed.
//					assertEquals("Incorrect exception thrown for invalid image file name", SWT.ERROR_INVALID_IMAGE, e);
				}
			}
		}
	
		try {
			String pathName = getPath(SwtTestUtil.invalidImageFilenames[1]);
			Image image = new Image(display, pathName);
			image.dispose();
			fail("No exception thrown for invalid file name");
		} catch (SWTException e) {
			assertSWTProblem("Incorrect exception thrown for invalid image file name", SWT.ERROR_INVALID_IMAGE, e);
		}		
	
		// create valid images
		for (int j = 0; j < displays.length; j++) {
//			Display tempDisplay = displays[j];
			int numFileNames = SwtTestUtil.imageFilenames.length;
			for (int k=0; k<numFileNames; k++) {
				fileName = SwtTestUtil.imageFilenames[k];
				for (int i=0; i<numFormats; i++) {
					String format = SwtTestUtil.imageFormats[i];
					String pathName = getPath(fileName + "." + format);
					Image image = new Image(display, pathName);
					image.dispose();
				}
			}
		}
	}
}

public void test_ConstructorLorg_eclipse_swt_graphics_Device_ImageFileNameProvider() {
	 // Null provider
	ImageFileNameProvider provider = null;
	try {
		Image image = new Image(display, provider);
		image.dispose();
		fail("No exception thrown for file name == null");
	} catch (IllegalArgumentException e) {
		assertSWTProblem("Incorrect exception thrown for provider == null", SWT.ERROR_NULL_ARGUMENT, e);
	}
	// Invalid provider
	provider = new ImageFileNameProvider() {
		public String getImagePath(int zoom) {
			return null;
		}
	};
	try {
		Image image = new Image(display, provider);
		image.dispose();
		fail("No exception thrown for non-existent file name");
	} catch (IllegalArgumentException e) {
		assertSWTProblem("Incorrect exception thrown for provider == null", SWT.ERROR_INVALID_ARGUMENT, e);
	}
	// Valid provider
	Image image = new Image(display, imageFileNameProvider);
	image.dispose();
	// Corrupt Image provider
	provider = new ImageFileNameProvider() {
		public String getImagePath(int zoom) {
			String fileName;
			switch (zoom) {
			case 100: 
				fileName = "corrupt.png"; break;
			case 150: 
				fileName = "corrupt.png"; break;
			case 200: 
				fileName = "corrupt.png"; break;
			default:
				return null;
			}
			return getPath(fileName);
		}
	};
	try {
		image = new Image(display, provider);
		image.dispose();
		fail("No exception thrown for corrupt image file.");
	} catch (SWTException e) {
		assertSWTProblem("Incorrect exception thrown for provider with corrupt images", SWT.ERROR_INVALID_IMAGE, e);
	}		
	// Valid provider only 100% zoom
	provider = new ImageFileNameProvider() {
		public String getImagePath(int zoom) {
			String fileName;
			switch (zoom) {
			case 100:
				fileName = "collapseall.png";
				break;
			case 150: 
			case 200: 
			default:
				return null;
			}
			return getPath(fileName);
		}
	};
	image = new Image(display, provider);
	image.dispose();
}

public void test_ConstructorLorg_eclipse_swt_graphics_Device_ImageDataProvider() {
	 // Null provider
	ImageDataProvider provider = null;
	try {
		Image image = new Image(display, provider);
		image.dispose();
		fail("No exception thrown for file name == null");
	} catch (IllegalArgumentException e) {
		assertSWTProblem("Incorrect exception thrown for provider == null", SWT.ERROR_NULL_ARGUMENT, e);
	}
	// Invalid provider
	provider = new ImageDataProvider() {
		public ImageData getImageData(int zoom) {
			return null;
		}
	};
	try {
		Image image = new Image(display, provider);
		image.dispose();
		fail("No exception thrown for non-existent file name");
	} catch (IllegalArgumentException e) {
		assertSWTProblem("Incorrect exception thrown for provider == null", SWT.ERROR_INVALID_ARGUMENT, e);
	}
	// Valid provider
	Image image = new Image(display, imageDataProvider);
	image.dispose();
	// Corrupt Image provider
	provider = new ImageDataProvider() {
		public ImageData getImageData(int zoom) {
			String fileName;
			switch (zoom) {
			case 100: 
				fileName = "corrupt.png"; break;
			case 150: 
				fileName = "corrupt.png"; break;
			case 200: 
				fileName = "corrupt.png"; break;
			default:
				return null;
			}
			return new ImageData(getPath(fileName));
		}
	};
	try {
		image = new Image(display, provider);
		image.dispose();
		fail("No exception thrown for corrupt image file.");
	} catch (SWTException e) {
		assertSWTProblem("Incorrect exception thrown for provider with corrupt images", SWT.ERROR_INVALID_IMAGE, e);
	}
	// Valid provider only 100% zoom
	provider = new ImageDataProvider() {
		public ImageData getImageData(int zoom) {
			String fileName;
			switch (zoom) {
			case 100:
				fileName = "collapseall.png";
				break;
			case 150: 
			case 200: 
			default:
				return null;
			}
			return new ImageData(getPath(fileName));
		}
	};
	image = new Image(display, provider);
	image.dispose();
}

public void test_equalsLjava_lang_Object() {
	Image image = null;
	Image image1 = null;

	try {
		image = new Image(display, 10, 10);
		image1 = image;
	
		assertFalse(":a:", image.equals(null));
		
		assertTrue(":b:", image.equals(image1));
		
		ImageData imageData = new ImageData(10, 10, 1, new PaletteData(new RGB[] {new RGB(0, 0, 0)}));
		image.dispose();
		image = new Image(display, imageData);
		image1 = new Image(display, imageData);
		assertFalse(":c:", image.equals(image1));
	} finally {
		image.dispose();
		image1.dispose();
	}
	
	// ImageFileNameProvider
	try {
		image = new Image(display, imageFileNameProvider);
		image1 = image;
		
		assertFalse(":d:", image.equals(null));
		
		assertTrue(":e:", image.equals(image1));
		
		image1 = new Image(display, imageFileNameProvider);
		assertTrue(":f:", image.equals(image1));
	} finally {
		image.dispose();
		image1.dispose();
	}
	try {
		image = new Image(display, imageFileNameProvider);
		image1 = image;
		
		assertFalse(":d:", image.equals(null));
		
		assertTrue(":e:", image.equals(image1));
		
		image1 = new Image(display, imageFileNameProvider);
		assertTrue(":f:", image.equals(image1));
	} finally {
		image.dispose();
		image1.dispose();
	}
	
	// ImageDataProvider
	try {
		image = new Image(display, imageDataProvider);
		image1 = image;
		
		assertFalse(":g:", image.equals(null));
		
		assertTrue(":h:", image.equals(image1));
		
		image1 = new Image(display, imageDataProvider);
		assertTrue(":i:", image.equals(image1));
	} finally {
		image.dispose();
		image1.dispose();
	}
}

public void test_getBackground() {
	Image image = new Image(display, 10, 10);
	image.dispose();
	try {
		image.getBackground();
		fail("No exception thrown for disposed image");
	} catch (SWTException e) {
		assertSWTProblem("Incorrect exception thrown for disposed image", SWT.ERROR_GRAPHIC_DISPOSED, e);
	}
	// remainder tested in setBackground method
}

public void test_getBounds() {
	Rectangle bounds = new Rectangle(0, 0, 10, 20);
	Image image = new Image(display, bounds.width, bounds.height);
	image.dispose();
	try {
		image.getBounds();
		fail("No exception thrown for disposed image");
	} catch (SWTException e) {
		assertSWTProblem("Incorrect exception thrown for disposed image", SWT.ERROR_GRAPHIC_DISPOSED, e);
	}
		
	// creates bitmap image
	image = new Image(display, bounds.width, bounds.height);
	Rectangle bounds1 = image.getBounds();
	image.dispose();
	assertEquals(":a:", bounds, bounds1);

	image = new Image(display, bounds);
	bounds1 = image.getBounds();
	image.dispose();
	assertEquals(":b:", bounds, bounds1);

	// create icon image
	ImageData imageData = new ImageData(bounds.width, bounds.height, 1, new PaletteData(new RGB[] {new RGB(0, 0, 0)}));
	image = new Image(display, imageData);
	bounds1 = image.getBounds();
	image.dispose();
	assertEquals(":c:", bounds, bounds1);
}

public void test_getImageData() {	
	getImageData1();
	getImageData2(24, new PaletteData(0xff0000, 0xff00, 0xff));		
	getImageData2(32, new PaletteData(0xff0000, 0xff00, 0xff));
}

public void test_hashCode() {
	Image image = null;
	Image image1 = null;

	try {
		image = new Image(display, 10, 10);
		image1 = image;
	
		assertEquals(":a:", image1.hashCode(), image.hashCode());
		
		ImageData imageData = new ImageData(10, 10, 1, new PaletteData(new RGB[] {new RGB(0, 0, 0)}));
		image.dispose();
		image = new Image(display, imageData);
		image1 = new Image(display, imageData);
		boolean equals = (image1.hashCode() == image.hashCode());
		assertFalse(":b:", equals);
	} finally {
		image.dispose();
		image1.dispose();
	}
	
	// ImageFileNameProvider
	try {
		image = new Image(display, imageFileNameProvider);
		image1 = new Image(display, imageFileNameProvider);
		assertEquals(":c:", image1.hashCode(), image.hashCode());
	} finally {
		image.dispose();
		image1.dispose();
	}
	
	// ImageDataProvider
	try {
		image = new Image(display, imageDataProvider);
		image1 = new Image(display, imageDataProvider);
		assertEquals(":d:", image1.hashCode(), image.hashCode());
	} finally {
		image.dispose();
		image1.dispose();
	}
}

public void test_isDisposed() {
	Image image = new Image(display, 10, 10);
	assertFalse(":a:", image.isDisposed());
	image.dispose();
	assertTrue(":b:", image.isDisposed());
}

public void test_setBackgroundLorg_eclipse_swt_graphics_Color() {
	if (SwtTestUtil.isGTK) {
		//TODO Fix GTK failure.
		if (SwtTestUtil.verbose) {
			System.out.println("Excluded test_setBackgroundLorg_eclipse_swt_graphics_Color(org.eclipse.swt.tests.junit.Test_org_eclipse_swt_graphics_Image)");
		}
		return;
	}
	
	Image image = new Image(display, 10, 10);

	try {
		image.setBackground(null);
		fail("No exception thrown for color == null");
	} catch (IllegalArgumentException e) {
		assertSWTProblem("Incorrect exception thrown for color == null", SWT.ERROR_NULL_ARGUMENT, e);
	} finally {
		image.dispose();
	}

	image = new Image(display, 10, 10);
	Color color = new Color(display, 255, 255, 255);
	color.dispose();
	try {
		image.setBackground(color);
		fail("No exception thrown for disposed color");
	} catch (IllegalArgumentException e) {
		assertSWTProblem("Incorrect exception thrown for disposed color", SWT.ERROR_INVALID_ARGUMENT, e);
	} finally {
		image.dispose();
	}

	image = new Image(display, 10, 10);
	image.dispose();
	color = new Color(display, 255, 255, 255);
	try {
		image.setBackground(color);
		fail("No exception thrown for disposed image");
	} catch (SWTException e) {
		assertSWTProblem("Incorrect exception thrown for disposed image", SWT.ERROR_GRAPHIC_DISPOSED, e);
	} finally {
		color.dispose();
	}
	
	// this image does not have a transparent pixel by default so setBackground has no effect
	image = new Image(display, 10, 10);
	image.setBackground(display.getSystemColor(SWT.COLOR_GREEN));
	color = image.getBackground();
	assertNull("background color should be null for non-transparent image", color);
	image.dispose();
	
	// create an image with transparency and then set the background color
	ImageData imageData = new ImageData(10, 10, 2, new PaletteData(new RGB[] {new RGB(0, 0, 0), new RGB(255, 255, 255), new RGB(50, 100, 150)}));
	imageData.transparentPixel = 0; // transparent pixel is currently black
	image = new Image(display, imageData);
	image.setBackground(display.getSystemColor(SWT.COLOR_GREEN));
	color = image.getBackground();
	assertEquals("background color should have been set to green", display.getSystemColor(SWT.COLOR_GREEN), color);
	image.dispose();
}

public void test_toString() {
	Image image = new Image(display, 10, 10);
	try {
		assertNotNull(image.toString());
		assertTrue(image.toString().length() > 0);
	} finally {
		image.dispose();
	}
}

/* custom */
Display display;

/** Test implementation **/

void getImageData1() {
	int numFormats = SwtTestUtil.imageFormats.length;
	String fileName = SwtTestUtil.imageFilenames[0];
	for (int i=0; i<numFormats; i++) {
		String format = SwtTestUtil.imageFormats[i];
		InputStream stream = SwtTestUtil.class.getResourceAsStream(fileName + "." + format);
		ImageData data1 = new ImageData(stream);
		Image image = new Image(display, data1);
		ImageData data2 = image.getImageData();
		image.dispose();
		assertEquals("Image width should be the same", data1.width, data2.width);
		assertEquals("Image height should be the same", data1.height, data2.height);
		try {
			stream.close();
		} catch (IOException e) {
			// continue;
		}
	}
}

/*
 * Verify Image.getImageData returns pixels with the same RGB value as the
 * source image. This test only makes sense with depth of 24 and 32 bits.
 */
void getImageData2(int depth, PaletteData palette) {
	int width = 10;
	int height = 10;
	Color color = new Color(display, 0, 0xff, 0);
	RGB colorRGB = color.getRGB();

	ImageData imageData = new ImageData(width, height, depth, palette);
	Image image = new Image(display, imageData);
		
	GC gc = new GC(image);
	gc.setBackground(color);
	gc.setForeground(color);
	gc.fillRectangle(0, 0, 10, 10);
		
	ImageData newData = image.getImageData();
	PaletteData newPalette = newData.palette;
	for (int i = 0; i < width; i++) {
		for (int j = 0; j < height; j++) {
			int pixel = newData.getPixel(i, j);
			RGB rgb = newPalette.getRGB(pixel);
			assertTrue("rgb.equals(colorRGB)", rgb.equals(colorRGB));
		}
	}
	color.dispose();
	gc.dispose();
	image.dispose();
}
String getPath(String fileName) {
	String urlPath;
	
	String pluginPath = System.getProperty("PLUGIN_PATH");
	if (pluginPath == null) {
		URL url = getClass().getClassLoader().getResource(fileName);
		if (url == null) {
			fail("URL == null for file " + fileName);
		}
		urlPath = url.getFile();
	} else {
		urlPath = pluginPath + "/data/" + fileName;
	}
	
	if (File.separatorChar != '/') urlPath = urlPath.replace('/', File.separatorChar);	
	if (SwtTestUtil.isWindows && urlPath.indexOf(File.separatorChar) == 0) urlPath = urlPath.substring(1);
	urlPath = urlPath.replaceAll("%20", " ");	
	
	return urlPath;
}
RGB getRealRGB(Color color) {
	Image colorImage = new Image(display, 10, 10);
	GC imageGc = new GC(colorImage);
	ImageData imageData;
	PaletteData palette;
	int pixel;
	
	imageGc.setBackground(color);
	imageGc.setForeground(color);
	imageGc.fillRectangle(0, 0, 10, 10);
	imageData = colorImage.getImageData();
	palette = imageData.palette;
	imageGc.dispose();
	colorImage.dispose();
	pixel = imageData.getPixel(0, 0);
	return palette.getRGB(pixel);
}

protected boolean isJ2ME() {
	try {
		Compatibility.newFileInputStream("");
	} catch (FileNotFoundException e) {
		return false;
	} catch (IOException e) {
	}
	return true;
}
}
