/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.swt.events;


import org.eclipse.swt.widgets.*;

/**
 * Instances of this class are sent as a result of
 * a touch-based gesture being generated by the user.
 * <p>
 * </p>
 *
 * @see GestureListener
 * @see <a href="http://www.eclipse.org/swt/">Sample code and further information</a>
 * @since 3.7
 */

public class GestureEvent extends TypedEvent {
	
	/**
	 * the state of the keyboard modifier keys and mouse masks
	 * at the time the event was generated.
	 * 
	 * @see org.eclipse.swt.SWT#MODIFIER_MASK
	 * @see org.eclipse.swt.SWT#BUTTON_MASK
	 */
	public int stateMask;
	
	/**
	 * The gesture type.
	 * <p><ul>
	 * <li>{@link org.eclipse.swt.SWT#GESTURE_BEGIN}</li>
	 * <li>{@link org.eclipse.swt.SWT#GESTURE_END}</li>
	 * <li>{@link org.eclipse.swt.SWT#GESTURE_MAGNIFY}</li>
	 * <li>{@link org.eclipse.swt.SWT#GESTURE_PAN}</li>
	 * <li>{@link org.eclipse.swt.SWT#GESTURE_ROTATE}</li>
	 * <li>{@link org.eclipse.swt.SWT#GESTURE_SWIPE}</li>
	 * </ul></p>
	 * 
	 * The value of the <code>detail</code> field determines which fields of the
	 * <code>GestureEvent</code> contain valid data.
	 */
	public int detail;

	/**
	 * Depending on the value of the <code>detail</code> field and the current platform, this field
	 * can mean the x coordinate of the centroid of the touches that make up the gesture (Windows), or the x coordinate of
	 * the screen location of the cursor at the time the gesture was performed (Mac OS X). 
	 * 
	 * NOTE: The definition of this field is subject to change before the API freeze for 3.7.
	 */
	public int x;
	
	/**
	 * Depending on the value of the <code>detail</code> field and the current platform, this field
	 * can mean the y coordinate of the centroid of the touches that make up the gesture (Windows), or the y component of
	 * the screen location of the cursor at the time the gesture was performed (Mac OS X).
	 * 
	 * NOTE: The definition of this field is subject to change before the API freeze for 3.7.
	 */	
	public int y;

	/**
	 * Number of degrees rotated on the device since the gesture started. Positive values indicated counter-clockwise
	 * rotation; negative values indicate clockwise rotation.
	 * 
	 * This field is valid when the <code>detail</code> field is set to <code>GESTURE_ROTATE</code>.
	 */
	public double rotation;

	/**
	 * The meaning of this field depends on the value of the <code>detail</code> field.
	 * 
	 * If <code>detail</code> is <code>GESTURE_SWIPE</code>
	 * and non-zero, a positive value indicates a swipe to the right, and a negative value indicates a swipe to the left.
	 * 
	 * If <code>detail</code> is <code>GESTURE_PAN</code> a positive value indicates a pan to the right of that many pixels,
	 * and a negative value indicates a pan to the left of that many pixels. 
	 * 
	 * This field is valid when the <code>detail</code> field is set to <code>GESTURE_SWIPE</code> or <code>GESTURE_PAN</code>.
	 * Both the <code>xDirection</code> and <code>yDirection</code> can be valid for an individual gesture.
	 */	
	public int xDirection;

	/**
	 * The meaning of this field depends on the value of the <code>detail</code> field.
	 * 
	 * If <code>detail</code> is <code>GESTURE_SWIPE</code>
	 * and non-zero, a positive value indicates a swipe down, and a negative value indicates a swipe up.
	 * 
	 * If <code>detail</code> is <code>GESTURE_PAN</code> a positive value indicates a pan downwards of that many pixels,
	 * and a negative value indicates a pan upwards of that many pixels. 
	 * 
	 * This field is valid when the <code>detail</code> field is set to <code>GESTURE_SWIPE</code> or <code>GESTURE_PAN</code>.
	 * Both the <code>xDirection</code> and <code>yDirection</code> can be valid for an individual gesture.
	 */	
	public int yDirection;

	/**
	 * Scale factor to be applied. In the first <code>GESTURE_MAGNIFY</code> received for a magnification this value will be 1.0
	 * and will then fluctuate as the user moves their fingers.
	 * 
	 * This field is valid when the <code>detail</code> field is set to <code>GESTURE_MAGNIFY</code>.
	 */
	public double magnification;

	/**
	 * A flag indicating whether the operation should be allowed.
	 * Setting this field to <code>false</code> will cancel the operation.
	 */
	public boolean doit;
	
	static final long serialVersionUID = -8348741538373572182L;
	
/**
 * Constructs a new instance of this class based on the
 * information in the given untyped event.
 *
 * @param e the untyped event containing the information
 */
public GestureEvent(Event e) {
	super(e);
	this.stateMask = e.stateMask;
	this.x = e.x;
	this.y = e.y;
	this.detail = e.detail;
	this.rotation = e.rotation;
	this.xDirection = e.xDirection;
	this.yDirection = e.yDirection;
	this.magnification = e.magnification;
	this.doit = e.doit;
}

/**
 * Returns a string containing a concise, human-readable
 * description of the receiver.
 *
 * @return a string representation of the event
 */
public String toString() {
	String string = super.toString ();
	return string.substring (0, string.length() - 1) // remove trailing '}'
		+ " stateMask=" + stateMask
		+ " detail=" + detail
		+ " x=" + x
		+ " y=" + y
		+ " rotation=" + rotation
		+ " xDirection=" + xDirection
		+ " yDirection=" + yDirection
		+ " magnification=" + magnification
		+ "}";
}
}
