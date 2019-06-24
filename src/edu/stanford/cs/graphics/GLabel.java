/*
 * File: GLabel.java
 * -----------------
 * This file exports a GObject subclass that displays a text string.
 */

package edu.stanford.cs.graphics;

import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

/**
 * The <code>GLabel</code> class is a graphical object whose appearance
 * consists of a text string.
 */

public class GLabel extends GObject {

/**
 * The default font used to display strings.  You can change the font by
 * invoking the <a href="#setFont(Font)"><code>setFont</code></a> method.
 */

   public static final Font DEFAULT_FONT = new Font("Default", Font.PLAIN, 12);

/**
 * Creates a new <code>GLabel</code> object initialized to contain the
 * specified string.
 *
 * @param str The initial contents of the <code>GLabel</code>
 */

   public GLabel(String str) {
      this(str, 0, 0);
   }

/**
 * Creates a new <code>GLabel</code> object with its baseline origin at the
 * specified position.
 *
 * @param str The initial contents of the <code>GLabel</code>
 * @param x The x-coordinate of the label origin
 * @param y The y-coordinate of the baseline for the label
 */

   public GLabel(String str, double x, double y) {
      label = str;
      setFont(DEFAULT_FONT);
      setLocation(x, y);
   }

/**
 * Changes the font used to display the <code>GLabel</code>.  This call
 * will usually change the size of the displayed object and will
 * therefore affect the result of calls to
 * <a href="GObject.html#getSize()"><code>getSize</code></a>
 * and <a href="GObject.html#getBounds()"><code>getBounds</code></a>.
 *
 * @param font A <code>Font</code> object indicating the new font
 */

   public void setFont(Font font) {
      labelFont = font;
      repaint();
   }

/**
 * Changes the font used to display the <code>GLabel</code> as specified by
 * the string <code>str</code>, which is interpreted in the style of
 * <code>Font.decode</code>.  The usual format of the font string is
 *
 *<pre>
 *    family-style-size
 *</pre>
 *
 * where both <code>style</code> and <code>size</code> are optional.
 * If any of these parts are specified as an asterisk, the existing
 * value is retained.
 *
 * @param str A <code>String</code> specifying the new font
 */

   public void setFont(String str) {
      setFont(Font.decode(str));
   }

/**
 * Returns the font in which the <code>GLabel</code> is displayed.
 *
 * @return The font in use by this object
 */

   public Font getFont() {
      return labelFont;
   }

/**
 * Changes the string stored within the <code>GLabel</code> object, so that
 * a new text string appears on the display.
 *
 * @param str The new string to display
 */

   public void setLabel(String str) {
      label = str;
      repaint();
   }

/**
 * Returns the string displayed by this object.
 *
 * @return The string displayed by this object
 */

   public String getLabel() {
      return label;
   }

/**
 * Implements the <code>paint2d</code> operation for this graphical object.
 * This method is not called directly by clients.
 */

   @Override
   protected void paint2d(Graphics2D g) {
      g.setFont(labelFont);
      g.drawString(label, 0, 0);
   }

/**
 * Returns the distance this string extends above the baseline.
 *
 * @return The ascent of this string in pixels
 */

   public double getAscent() {
      return getFontMetrics().getAscent();
   }

/**
 * Returns the distance this string descends below the baseline.
 *
 * @return The descent of this string in pixels
 */

   public double getDescent() {
      return getFontMetrics().getDescent();
   }

/**
 * Returns a <code>FontMetrics</code> object describing the dimensions of
 * this string.
 *
 * @return A <code>FontMetrics</code> object describing the dimensions of
 *         this string
 */

   public FontMetrics getFontMetrics() {
      Component comp = getComponent();
      if (comp == null) comp = DUMMY_COMPONENT;
      return comp.getFontMetrics(labelFont);
   }

/**
 * Returns a string indicating the parameters of this object.
 */

   @Override
   public String paramString() {
      return super.paramString() + ", string=\"" + label + "\"";
   }

/* Protected methods */

/**
 * Returns the bounding box of the object relative to its origin.
 */

   @Override
   protected GRectangle localBounds(GTransform ctm) {
      FontMetrics fm = getFontMetrics();
      double y0 = -fm.getAscent();
      double width = fm.stringWidth(label);
      double height = fm.getHeight();
      GRectangle bb = new GRectangle(ctm.transform(0, y0));
      bb.add(ctm.transform(width, y0));
      bb.add(ctm.transform(0, y0 + height));
      bb.add(ctm.transform(width, y0 + height));
      return bb;
   }

/**
 * Checks to see whether a point is inside the object.
 */

   @Override
   protected boolean localContains(double x, double y) {
      FontMetrics fm = getFontMetrics();
      double y0 = -fm.getAscent();
      double width = fm.stringWidth(label);
      double height = fm.getHeight();
      return x >= 0 && x < width && y >= y0 && y < y0 + height;
   }

/* Private instance variables */

   private String label;
   private Font labelFont;

   private static final Component
     DUMMY_COMPONENT = GImageTools.getImageObserver();

}
