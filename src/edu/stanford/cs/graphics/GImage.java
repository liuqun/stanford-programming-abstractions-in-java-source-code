/*
 * File: GImage.java
 * -----------------
 * This class exports a GObject subclass that displays an image.
 */

package edu.stanford.cs.graphics;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;

/**
 * The <code>GImage</code> class is a graphical object whose appearance is
 * defined by an image.
 */

public class GImage extends GObject implements GResizable {

/**
 * Creates a new <code>GImage</code> object at the origin that displays
 * the specified image.
 *
 * @param image The image to use as the contents of this <code>GImage</code>
 */

   public GImage(Image image) {
      this(image, 0, 0);
   }

/**
 * Creates a new <code>GImage</code> object by looking for an image with that
 * name.  The search for an image by name consists of the following steps:
 *
 * <p><ol>
 * <li>Check to see if an image with that name has already been defined.  If
 *     so, return that image.<p>
 *
 * <li>Check to see if there is a resource available with that name whose
 *     contents can be read as an <code>Image</code>.  If so, read the image
 *     from the resource file.<p>
 *
 * <li>Load the image from a file with the specified name, relative to the
 *     application directory or the applet code base.
 * </ol><p>
 *
 * @param name The name used to search for the contents of this image
 */

   public GImage(String name) {
      this(name, 0, 0);
   }

/**
 * Creates a new <code>GImage</code> object at the origin.  The
 * <code>array</code> parameter is a two-dimensional pixel array in
 * which each pixel value consists of an integer that is subdivided
 * into four eight-bit bytes, as follows:
 *
 * <center>
 *    <i>alpha</i> <code>&lt;&lt;</code> 24  <code>|</code>
 *    <i>red</i>   <code>&lt;&lt;</code> 16  <code>|</code>
 *    <i>green</i> <code>&lt;&lt;</code>  8  <code>|</code>
 *    <i>blue</i>
 * </center>
 *
 * The <i>alpha</i> value indicates the transparency, and the other values
 * are the red, green, and blue components of the color.
 *
 * @param array A two-dimensional pixel array
 */

   public GImage(int[][] array) {
      this(array, 0, 0);
   }

/**
 * Creates a new <code>GImage</code> object at the specified coordinates.
 * The <code>image</code> parameter is used to initialize the appearance
 * of the image.
 *
 * @param image The image to use as the contents of this <code>GImage</code>
 * @param x The x-coordinate of the upper left corner of the image
 * @param y The y-coordinate of the upper left corner of the image
 */

   public GImage(Image image, double x, double y) {
      setImage(image);
      setLocation(x, y);
   }

/**
 * Creates a new <code>GImage</code> object at the specified coordinates.  The
 * <code>name</code> parameter is used to identify an image to display, as
 * described in the single-argument version of the
 * <a href="#GImage(String)"><code>GImage</code></a> constructor.
 *
 * @param name The name used to search for the contents of this image
 * @param x The x-coordinate of the upper left corner of the image
 * @param y The y-coordinate of the upper left corner of the image
 */

   public GImage(String name, double x, double y) {
      this(GImageTools.loadImage(name), x, y);
   }

/**
 * Creates a new <code>GImage</code> object at the specified coordinates.
 * The <code>array</code> parameter is a two-dimensional pixel array in
 * which each pixel value consists of an integer that is subdivided into
 * four eight-bit bytes, as follows:
 *
 * <center>
 *    <i>alpha</i> <code>&lt;&lt;</code> 24  <code>|</code>
 *    <i>red</i>   <code>&lt;&lt;</code> 16  <code>|</code>
 *    <i>green</i> <code>&lt;&lt;</code>  8  <code>|</code>
 *    <i>blue</i>
 * </center>
 *
 * The <i>alpha</i> value indicates the transparency, and the other values
 * are the red, green, and blue components of the color.
 *
 * @param array A two-dimensional pixel array
 * @param x The x-coordinate of the upper left corner of the image
 * @param y The y-coordinate of the upper left corner of the image
 */

   public GImage(int[][] array, double x, double y) {
      this(GImageTools.createImage(array), x, y);
   }

/**
 * Resets the image used by this <code>GImage</code> object to the new image
 * specified as an argument.  Calling <code>setImage</code> automatically
 * changes the size of the image to be equal to that of the image data.
 *
 * @param image The image to use as the contents of this <code>GImage</code>
 */

   public void setImage(Image image) {
      this.image = GImageTools.loadImage(image);
      sizeDetermined = false;
      determineSize();
      repaint();
   }

/**
 * Resets the image used by this <code>GImage</code> object to the one
 * identified by the argument <code>name</code>, which is processed
 * as described in the constructors.  Calling <code>setImage</code>
 * automatically changes the size of the image to be equal to that of
 * the image data.
 *
 * @param name The name used to search for the contents of this image
 */

   public void setImage(String name) {
      setImage(GImageTools.loadImage(name));
   }

/**
 * Returns the image stored inside this <code>GImage</code>.
 *
 * @return The <code>Image</code> object stored inside this <code>GImage</code>
 */

   public Image getImage() {
      return image;
   }

/**
 * Saves the image to a file with the specified filename.  The data format
 * for the image file is determined by the suffix of the filename.  If the
 * suffix of the file is not recognized as a supported image type, calling
 * this method generates an error.
 *
 * @param filename The name of the file to which the image is saved
 */

   public void saveImage(String filename) {
      GImageTools.saveImage(image, filename);
   }

/**
 * Saves the image to the specified file.  The data format for the
 * image file is determined by the suffix of the filename.  If the
 * suffix of the file is not recognized as a supported image type,
 * calling this method generates an error.
 *
 * @param file The <code>File</code> to which the image is saved
 */

   public void saveImage(File file) {
      GImageTools.saveImage(image, file);
   }

/**
 * Changes the size of the object so that it has the specified width
 * and height.  This method throws a runtime exception if the object
 * has been transformed.
 *
 * @param width The new width of the object
 * @param height The new height of the object
 */

   @Override
   public final void setSize(double width, double height) {
      if (isTransformed()) {
         throw new RuntimeException("setSize called on transformed object");
      }
      this.width = width;
      this.height = height;
      repaint();
   }

/**
 * Changes the size of this object to match that of the specified
 * <code>GDimension</code> object.  This method throws a runtime
 * exception if the object has been transformed.
 *
 * @param size A <code>GDimension</code> object specifying the new size
 */

   @Override
   public final void setSize(GDimension size) {
      setSize(size.getWidth(), size.getHeight());
   }

/**
 * Changes the bounds of this object to the specified values.  This method
 * throws a runtime exception if the object has been transformed.
 *
 * @param x The new x-coordinate for the untransformed object
 * @param y The new y-coordinate for the untransformed object
 * @param width The new width of the untransformed object
 * @param height The new height of the untransformed object
 */

   @Override
   public void setBounds(double x, double y, double width, double height) {
      if (isTransformed()) {
         throw new RuntimeException("setBounds called on transformed object");
      }
      this.width = width;
      this.height = height;
      setLocation(x, y);
   }

/**
 * Changes the bounds of this object to match the specified
 * <code>GRectangle</code>.  This method throws a runtime exception if
 * the object has been transformed.
 *
 * @param bounds A <code>GRectangle</code> specifying the new bounds
 */

   @Override
   public final void setBounds(GRectangle bounds) {
      setBounds(bounds.getX(), bounds.getY(),
                bounds.getWidth(), bounds.getHeight());
   }

/**
 * Returns a two-dimensional array of pixel values from the stored image.
 *
 * @return A two-dimensional array of pixel values from the stored image
 */

   public int[][] getPixelArray() {
      return GImageTools.getPixelArray(image);
   }

/**
 * Returns the alpha component from an RGB value.
 *
 * @param pixel An <code>int</code> containing a pixel value as
 *              alpha/red/green/blue
 * @return The alpha component of the pixel
 */

   public static int getAlpha(int pixel) {
      return (pixel >> 24) & 0xFF;
   }

/**
 * Returns the red component from an RGB value.
 *
 * @param pixel An <code>int</code> containing a pixel value as
 *              alpha/red/green/blue
 * @return The red component of the pixel
 */

   public static int getRed(int pixel) {
      return (pixel >> 16) & 0xFF;
   }

/**
 * Returns the green component from an RGB value.
 *
 * @param pixel An <code>int</code> containing a pixel value as
 *               alpha/red/green/blue
 * @return The green component of the pixel
 */

   public static int getGreen(int pixel) {
      return (pixel >> 8) & 0xFF;
   }

/**
 * Returns the blue component from an RGB value.
 *
 * @param pixel An <code>int</code> containing a pixel value as
 *              alpha/red/green/blue
 * @return The blue component of the pixel
 */

   public static int getBlue(int pixel) {
      return pixel & 0xFF;
   }

/**
 * Creates an opaque pixel value with the color components given by
 * <code>red</code>, <code>green</code>, and <code>blue</code>.
 *
 * @param red The red component of the pixel (0 to 255)
 * @param green The green component of the pixel (0 to 255)
 * @param blue The blue component of the pixel (0 to 255)
 * @return An opaque pixel value containing these components
 */

   public static int createRGBPixel(int red, int green, int blue) {
      return createRGBPixel(red, green, blue, 0xFF);
   }

/**
 * Creates a pixel value with the color components given by
 * <code>red</code>, <code>green</code>, and <code>blue</code>
 * and the transparency value <code>alpha</code>.
 *
 * @param red The red component of the pixel (0 to 255)
 * @param green The green component of the pixel (0 to 255)
 * @param blue The blue component of the pixel (0 to 255)
 * @param alpha The transparency value of the pixel (0 to 255)
 * @return A pixel value containing these components
 */

   public static int createRGBPixel(int red, int green, int blue, int alpha) {
      return (alpha << 24) | (red << 16) | (green << 8) | blue;
   }

/* Protected methods */

/**
 * Returns the bounding box of the object relative to its origin.
 */

   @Override
   protected GRectangle localBounds(GTransform ctm) {
      determineSize();
      GRectangle bb = new GRectangle(ctm.transform(0, 0));
      bb.add(ctm.transform(width, 0));
      bb.add(ctm.transform(0, height));
      bb.add(ctm.transform(width, height));
      return bb;
   }

/**
 * Checks to see whether a point is inside the object.
 */

   @Override
   protected boolean localContains(double x, double y) {
      determineSize();
      return x >= 0 && x < width && y >= 0 && y < height;
   }

/**
 * Implements the <code>paint2d</code> operation for this graphical object.
 * This method is not called directly by clients.
 */

   @Override
   protected void paint2d(Graphics2D g) {
      Component imageObserver = getComponent();
      if (imageObserver == null) {
         imageObserver = GImageTools.getImageObserver();
      }
      if (image != null && imageObserver != null) {
         determineSize();
         Color color = getObjectColor();
         if (color == null) {
            g.drawImage(image, 0, 0,
                        GMath.round(width), GMath.round(height),
                        imageObserver);
         } else {
            g.drawImage(image, 0, 0,
                        GMath.round(width), GMath.round(height),
                        color, imageObserver);
         }
      }
   }

/* Private methods */

/**
 * Computes the size of the image.
 */

   private void determineSize() {
      if (sizeDetermined) return;
      Component component = getComponent();
      if (component == null) component = GImageTools.getImageObserver();
      width = image.getWidth(component);
      height = image.getHeight(component);
      sizeDetermined = true;
   }

/* Private instance variables */

   private Image image;
   private double width;
   private double height;
   private boolean sizeDetermined;

}
