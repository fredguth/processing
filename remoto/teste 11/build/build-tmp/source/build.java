import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import processing.pdf.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class build extends PApplet {


HDrawablePool pool1, pool2, pool3;
HPixelColorist colors1;
HColorPool colors2;
HCanvas c1,c2,c3;


public void setup(){
	
	H.init(this).background(0xff66ccff);
	c1 = new HCanvas().autoClear(true);
	c2 = new HCanvas().autoClear(true);
	c3 = new HCanvas().autoClear(true);

	H.add(c1);
	H.add(c2);
	H.add(c3);
	
	colors1 = new HPixelColorist("mancha2.png").fillOnly();
	colors2 = new HColorPool(0xffFFFFFF, 0xff0066ff, 0xff003366);
}

public void draw(){
	pool1 = new HDrawablePool(100);
	pool2 = new HDrawablePool(100);
	pool3 = new HDrawablePool(100);
	pool1.autoParent(c1)
		.add(new HShape("muda1.svg"),16)
		.add(new HShape("muda2.svg"),16)
		.add(new HShape("muda3.svg"),16)
		.add(new HShape("muda4.svg"),16)
		.add(new HShape("muda5.svg"),16)
		.add(new HShape("muda6.svg"),16)
		.add(new HShape("muda7.svg"),1)
		.add(new HShape("muda8.svg"),1)
		.add(new HShape("muda9.svg"),1)

		// .add(new HShape("svg2.svg"))
		// .add(new HShape("svg3.svg"))
		// .add(new HShape("svg4.svg"))
		// .add(new HShape("svg5.svg"))
		// .add(new HShape("svg6.svg"))

		.layout(
			new HGridLayout()
			.startX(50)
			.startY(50)
			.spacing(100,100)
			.cols(5)
		)

		.onRequest(
			new HCallback() {
				public void run(Object obj) {
					HShape d = (HShape) obj;
					d
						.enableStyle(false)
						.noStroke()
						.anchorAt(H.CENTER)
						.size(100,100)
						// .fill(colors.getColor(d.x(), d.y()))
					;
					d.rotate( (int)random(4) * 90 );
					if (colors1.getColor(d.x(), d.y()) <= (0xff666666)) {
						// log(colors1.getColor(d.x(), d.y()));
						d.randomColors(colors2.fillOnly());
					}
					else {
						d.fill(0xffFFFFFF);
						d.alpha(0);
					}

					//d.randomColors(colors1.fillOnly());
				}
			}
		)
	;

	pool2.autoParent(c2)
		.add(new HShape("muda1.svg"),1)
		.add(new HShape("muda2.svg"),1)
		.add(new HShape("muda3.svg"),1)
		.add(new HShape("muda4.svg"),1)
		.add(new HShape("muda5.svg"),1)
		.add(new HShape("muda6.svg"),1)
		.add(new HShape("muda7.svg"),16)
		.add(new HShape("muda8.svg"),16)
		.add(new HShape("muda9.svg"),16)

		// .add(new HShape("svg2.svg"))
		// .add(new HShape("svg3.svg"))
		// .add(new HShape("svg4.svg"))
		// .add(new HShape("svg5.svg"))
		// .add(new HShape("svg6.svg"))

		.layout(
			new HGridLayout()
			.startX(50)
			.startY(50)
			.spacing(100,100)
			.cols(5)
		)

		.onRequest(
			new HCallback() {
				public void run(Object obj) {
					HShape d = (HShape) obj;
					d
						.enableStyle(false)
						.noStroke()
						.anchorAt(H.CENTER)
						.size(100,100)
						// .fill(colors1.getColor(d.x(), d.y()))
					;
					d.rotate( (int)random(4) * 90 );
					if (colors1.getColor(d.x(), d.y()) >= (0xff666666) &&
							colors1.getColor(d.x(), d.y()) <= (0xffCCCCCC)	) {
						// log(colors1.getColor(d.x(), d.y()));
						d.randomColors(colors2.fillOnly());
					}
					else {
						d.fill(0xffFFFFFF);
						d.alpha(0);
					}

					//d.randomColors(colors1.fillOnly());
				}
			}
		)
	;


	if (mousePressed) {
 		pool1.shuffleRequestAll();
 		pool2.shuffleRequestAll();
 		//pool3.shuffleRequestAll();
 		H.drawStage();
    pool1.drain(true);
    pool2.drain(true);
    //pool3.drain(true);
  }


}

public void saveVector() {
	PGraphics tmp = beginRecord(PDF, "render.pdf");

	H.stage().paintAll(tmp, false, 1); // PGraphics, uses3D, alpha


	endRecord();
}
public void keyPressed() {
	if (key == 's') {
		saveVector();
	}
}
/*
 * HYPE_Processing
 * http:
 * 
 * Copyright (c) 2013 Joshua Davis & James Cruz
 * 
 * Distributed under the BSD License. See LICENSE.txt for details.
 * 
 * All rights reserved.
 */
public static class H implements HConstants {
	private static H _self;
	private static PApplet _app;
	private static PGraphics _graphicsContext;
	private static HStage _stage;
	private static HBehaviorRegistry _behaviors;
	private static HMouse _mouse;
	private static boolean _uses3D;
	public static H init(PApplet applet) {
		_app = applet;
		if(_self == null) _self = new H();
		if(_stage == null) _stage = new HStage(_app);
		if(_behaviors == null) _behaviors = new HBehaviorRegistry();
		if(_mouse == null) _mouse = new HMouse(_app);
		try {
			/* HACK
			 * This arbitrary reference to a field in _app.g will throw an error
			 * in Processing.js as `g` doesn't exist in that mode.
			 */
			int dummyVar = _app.g.A;
			_graphicsContext = _app.g;
		} catch(Exception e) {
			/* This roundabout assignment bypasses java's compile-time type
			 * checking. Normally, this would cause a class-cast exception in
			 * java, but js mode doesn't mind due to duck-typing.
			 */
			Object o = _app;
			_graphicsContext = (PGraphics) o;
		}
		return _self;
	}
	public static HStage stage() {
		return _stage;
	}
	public static PApplet app() {
		return _app;
	}
	public static HBehaviorRegistry behaviors() {
		return _behaviors;
	}
	public static HMouse mouse() {
		return _mouse;
	}
	public static H use3D(boolean b) {
		_uses3D = b;
		return _self;
	}
	public static boolean uses3D() {
		return _uses3D;
	}
	public static H background(int clr) {
		_stage.background(clr);
		return _self;
	}
	public static H backgroundImg(Object arg) {
		_stage.backgroundImg(arg);
		return _self;
	}
	/** @deprecated */
	public static H autoClear(boolean b) {
		_stage.autoClear(b);
		return _self;
	}
	public static H autoClears(boolean b) {
		_stage.autoClears(b);
		return _self;
	}
	public static boolean autoClears() {
		return _stage.autoClears();
	}
	public static H clearStage() {
		_stage.clear();
		return _self;
	}
	public static HCanvas add(HCanvas stageChild) {
		return (HCanvas) _stage.add(stageChild);
	}
	public static HEllipse add(HEllipse stageChild) {
		return (HEllipse) _stage.add(stageChild);
	}
	public static HGroup add(HGroup stageChild) {
		return (HGroup) _stage.add(stageChild);
	}
	public static HImage add(HImage stageChild) {
		return (HImage) _stage.add(stageChild);
	}
	public static HPath add(HPath stageChild) {
		return (HPath) _stage.add(stageChild);
	}
	public static HRect add(HRect stageChild) {
		return (HRect) _stage.add(stageChild);
	}
	public static HShape add(HShape stageChild) {
		return (HShape) _stage.add(stageChild);
	}
	public static HText add(HText stageChild) {
		return (HText) _stage.add(stageChild);
	}
	public static HDrawable add(HDrawable stageChild) {
		return _stage.add(stageChild);
	}
	public static HCanvas remove(HCanvas stageChild) {
		return (HCanvas) _stage.remove(stageChild);
	}
	public static HEllipse remove(HEllipse stageChild) {
		return (HEllipse) _stage.remove(stageChild);
	}
	public static HGroup remove(HGroup stageChild) {
		return (HGroup) _stage.remove(stageChild);
	}
	public static HImage remove(HImage stageChild) {
		return (HImage) _stage.remove(stageChild);
	}
	public static HPath remove(HPath stageChild) {
		return (HPath) _stage.remove(stageChild);
	}
	public static HRect remove(HRect stageChild) {
		return (HRect) _stage.remove(stageChild);
	}
	public static HShape remove(HShape stageChild) {
		return (HShape) _stage.remove(stageChild);
	}
	public static HText remove(HText stageChild) {
		return (HText) _stage.remove(stageChild);
	}
	public static HDrawable remove(HDrawable stageChild) {
		return _stage.remove(stageChild);
	}
	public static H drawStage() {
		_behaviors.runAll(_app);
		_mouse.handleEvents();
		_stage.paintAll(_graphicsContext, _uses3D, 1);
		return _self;
	}
	public static boolean mouseStarted() {
		return _mouse.started();
	}
	public static PImage getImage(Object imgArg) {
		if(imgArg instanceof PImage)
			return (PImage) imgArg;
		if(imgArg instanceof HImageHolder)
			return ((HImageHolder)imgArg).image();
		if(imgArg instanceof String)
			return _app.loadImage((String) imgArg);
		return null;
	}
	private H() {}
}
/*
 * HYPE_Processing
 * http:
 * 
 * Copyright (c) 2013 Joshua Davis & James Cruz
 * 
 * Distributed under the BSD License. See LICENSE.txt for details.
 * 
 * All rights reserved.
 */
public static abstract class HBehavior extends HNode<HBehavior> {
	protected HBehaviorRegistry _registry;
	public HBehavior register() {
		H.behaviors().register(this);
		return this;
	}
	public HBehavior unregister() {
		H.behaviors().unregister(this);
		return this;
	}
	public boolean poppedOut() {
		return _registry == null;
	}
	public void popOut() {
		super.popOut();
		_registry = null;
	}
	public void swapLeft() {
		if(_prev._prev == null) return;
		super.swapLeft();
	}
	public void putAfter(HBehavior dest) {
		if(dest._registry == null) return;
		super.putAfter(dest);
		_registry = dest._registry;
	}
	public void putBefore(HBehavior dest) {
		if(dest._registry == null) return;
		super.putBefore(dest);
		_registry = dest._registry;
	}
	public void replaceNode(HBehavior target) {
		super.replaceNode(target);
		_registry = target._registry;
		target._registry = null;
	}
	public abstract void runBehavior(PApplet app);
}
/*
 * HYPE_Processing
 * http:
 * 
 * Copyright (c) 2013 Joshua Davis & James Cruz
 * 
 * Distributed under the BSD License. See LICENSE.txt for details.
 * 
 * All rights reserved.
 */
public static class HBehaviorRegistry {
	private HBehaviorSentinel _firstSentinel;
	public HBehaviorRegistry() {
		_firstSentinel = new HBehaviorSentinel(this);
	}
	public boolean isRegistered(HBehavior b) {
		return (b._registry != null && b._registry.equals(this));
	}
	public void register(HBehavior b) {
		if(b.poppedOut()) b.putAfter(_firstSentinel);
	}
	public void unregister(HBehavior b) {
		if(isRegistered(b)) b.popOut();
	}
	public void runAll(PApplet app) {
		HBehavior n = _firstSentinel.next();
		while(n != null) {
			n.runBehavior(app);
			n = n.next();
		}
	}
	public static class HBehaviorSentinel extends HBehavior {
		public HBehaviorSentinel(HBehaviorRegistry r) {
			_registry = r;
		}
		public void runBehavior(PApplet app) {}
	}
}
/*
 * HYPE_Processing
 * http:
 * 
 * Copyright (c) 2013 Joshua Davis & James Cruz
 * 
 * Distributed under the BSD License. See LICENSE.txt for details.
 * 
 * All rights reserved.
 */
public static class HBundle {
	private HashMap<String,Object> objectContents;
	private HashMap<String,Float> numberContents;
	public HBundle() {
		objectContents = new HashMap<String,Object>();
		numberContents = new HashMap<String,Float>();
	}
	public HBundle obj(String key, Object value) {
		objectContents.put(key,value);
		return this;
	}
	public HBundle num(String key, float value) {
		numberContents.put(key,value);
		return this;
	}
	public HBundle bool(String key, boolean value) {
		numberContents.put(key, (value? 1f : 0f) );
		return this;
	}
	public Object obj(String key) {
		return objectContents.get(key);
	}
	public String str(String key) {
		Object o = objectContents.get(key);
		if(o instanceof String)
			return (String) o;
		return null;
	}
	public float num(String key) {
		return numberContents.get(key);
	}
	public int numI(String key) {
		return Math.round(numberContents.get(key));
	}
	public boolean bool(String key) {
		return (numberContents.get(key) != 0);
	}
}
/*
 * HYPE_Processing
 * http:
 * 
 * Copyright (c) 2013 Joshua Davis & James Cruz
 * 
 * Distributed under the BSD License. See LICENSE.txt for details.
 * 
 * All rights reserved.
 */
public static interface HCallback {
	public void run(Object obj);
}
/*
 * HYPE_Processing
 * http:
 * 
 * Copyright (c) 2013 Joshua Davis & James Cruz
 * 
 * Distributed under the BSD License. See LICENSE.txt for details.
 * 
 * All rights reserved.
 */
public static class HCanvas extends HDrawable {
	private PGraphics _graphics;
	private String _renderer;
	private float _filterParam;
	private int _filterKind, _blendMode, _fadeAmt;
	private boolean _autoClear,_hasFade,_hasFilter,_hasFilterParam,_hasBlend, _hasShader;
	private ArrayList<PShader> _shader;
	public HCanvas() {
		this(H.app().width, H.app().height);
	}
	public HCanvas(String bufferRenderer) {
		this(H.app().width, H.app().height, bufferRenderer);
	}
	public HCanvas(float w, float h) {
		this(w, h, PConstants.JAVA2D);
	}
	public HCanvas(float w, float h, String bufferRenderer) {
		_renderer = bufferRenderer;
		size(w,h);
	}
	public HCanvas createCopy() {
		HCanvas copy = new HCanvas(_width,_height,_renderer);
		copy.autoClear(_autoClear).hasFade(_hasFade);
		if(_hasFilter) copy.filter(_filterKind, _filterParam);
		if(_hasBlend) copy.blend(_blendMode);
		copy.copyPropertiesFrom(this);
		return copy;
	}
	protected void updateBuffer() {
		int w = Math.round(_width);
		int h = Math.round(_height);
		_graphics = H.app().createGraphics(w, h, _renderer);
		_graphics.loadPixels();
		_graphics.beginDraw();
			_graphics.background(H.CLEAR);
		_graphics.endDraw();
		_width = w;
		_height = h;
	}
	public HCanvas renderer(String s) {
		_renderer = s;
		updateBuffer();
		return this;
	}
	public String renderer() {
		return _renderer;
	}
	public boolean usesZ() {
		return _renderer.equals(PConstants.P3D) ||
			_renderer.equals(PConstants.OPENGL);
	}
	public PGraphics graphics() {
		return _graphics;
	}
	public HCanvas shader(PShader s) {
		if (!_hasShader) {
			_hasShader = true;
			_shader = new ArrayList<PShader>();
		}
		_shader.add(s);
		return this;
	}
	public HCanvas filter(int kind) {
		_hasFilter = true;
		_hasFilterParam = false;
		_filterKind = kind;
		return this;
	}
	public HCanvas filter(int kind, float param) {
		_hasFilter = true;
		_hasFilterParam = true;
		_filterKind = kind;
		_filterParam = param;
		return this;
	}
	public HCanvas noFilter() {
		_hasFilter = false;
		return this;
	}
	public boolean hasFilter() {
		return _hasFilter;
	}
	public HCanvas filterKind(int i) {
		_filterKind = i;
		return this;
	}
	public int filterKind() {
		return _filterKind;
	}
	public HCanvas filterParam(float f) {
		_filterParam = f;
		return this;
	}
	public float filterParam() {
		return _filterParam;
	}
	public HCanvas blend() {
		return blend(PConstants.BLEND);
	}
	public HCanvas blend(int mode) {
		_hasBlend = true;
		_blendMode = mode;
		return this;
	}
	public HCanvas noBlend() {
		_hasBlend = false;
		return this;
	}
	public HCanvas hasBlend(boolean b) {
		return (b)? blend() : noBlend();
	}
	public boolean hasBlend() {
		return _hasBlend;
	}
	public HCanvas blendMode(int i) {
		_blendMode = i;
		return this;
	}
	public int blendMode() {
		return _blendMode;
	}
	public HCanvas fade(int fadeAmt) {
		_hasFade = true;
		_fadeAmt = fadeAmt;
		return this;
	}
	public HCanvas noFade() {
		_hasFade = false;
		return this;
	}
	public HCanvas hasFade(boolean b) {
		_hasFade = b;
		return this;
	}
	public boolean hasFade() {
		return _hasFade;
	}
	public HCanvas autoClear(boolean b) {
		_autoClear = b;
		return this;
	}
	public boolean autoClear() {
		return _autoClear;
	}
	public HCanvas background(int clr) {
		return (HCanvas) fill(clr);
	}
	public HCanvas background(int clr, int alpha) {
		return (HCanvas) fill(clr, alpha);
	}
	public HCanvas background(int r, int g, int b) {
		return (HCanvas) fill(r, g, b);
	}
	public HCanvas background(int r, int g, int b, int a) {
		return (HCanvas) fill(r, g, b, a);
	}
	public int background() {
		return _fill;
	}
	public HCanvas noBackground() {
		return (HCanvas) noFill();
	}
	public HCanvas size(float w, float h) {
		super.width(w);
		super.height(h);
		updateBuffer();
		return this;
	}
	public HCanvas width(float w) {
		super.width(w);
		updateBuffer();
		return this;
	}
	public HCanvas height(float h) {
		super.height(h);
		updateBuffer();
		return this;
	}
	public void paintAll(PGraphics g, boolean zFlag, float alphaPc) {
		if(_alphaPc<=0 || _width==0 || _height==0) return;
		g.pushMatrix();
			if(zFlag) g.translate(_x,_y,_z);
			else g.translate(_x,_y);
			g.rotate(_rotationZRad);
			alphaPc *= _alphaPc;
			_graphics.beginDraw();
			if(_autoClear) {
				_graphics.clear();
			} else {
				if(_hasFilter) {
					if(_hasFilterParam) _graphics.filter(_filterKind,_filterParam);
					else _graphics.filter(_filterKind);
				}
				if(_hasFade) {
					if(!_renderer.equals(PConstants.JAVA2D))
						_graphics.loadPixels();
					int[] pix = _graphics.pixels;
					for(int i=0; i<pix.length; ++i) {
						int clr = pix[i];
						int a = clr >>> 24;
						if(a == 0) continue;
						a -= _fadeAmt;
						if(a < 0) a = 0;
						pix[i] = clr & 0xFFFFFF | (a << 24);
					}
					_graphics.updatePixels();
				}
				if(_hasBlend) {
					_graphics.blend(
						0,0, _graphics.width,_graphics.height,
						0,0, _graphics.width,_graphics.height, _blendMode);
				}
			}
			HDrawable child = _firstChild;
			while(child != null) {
				child.paintAll(_graphics, usesZ(), alphaPc);
				child = child.next();
			}
			if (_hasShader) {
				for(PShader s : _shader) {
					_graphics.filter(s);
				}
			}
			_graphics.endDraw();
			g.image(_graphics,0,0);
		g.popMatrix();
	}
	public void draw(PGraphics g,boolean b,float x,float y,float f) {}
}
public static class HCapture {
	private PGraphics _capturer;
	private String _renderer;
	private String _filename;
	private boolean _isRecording;
	private int  _start,        _end;
	public HCapture() {
		_start = _end = -1;
	}
	public HCapture capture() {
		_start = _end = H.app().frameCount;
		return this;
	}
	public HCapture capture(int frameNum) {
		_start = _end = H.app().frameCount;
		return this;
	}
	public HCapture start(int frameNum) {
		_start = frameNum;
		return this;
	}
	public int start() {
		return _start;
	}
	public HCapture end(int frameNum) {
		_end = frameNum;
		return this;
	}
	public int end() {
		return _end;
	}
	/*
	public HCapture startKey(char keyChar) {
		_startKey = keyChar;
		_start = -1;
		return this;
	}
	public int startKey() {
		return _startKey;
	}
	public HCapture endKey(char keyChar) {
		_endKey = keyChar;
		_end = -1;
		return this;
	}
	public int endKey() {
		return _endKey;
	}
	*/
	public boolean isRecording() {
		return _isRecording;
	}
	public HCapture filename(String s) {
		_filename = s;
		return this;
	}
	public String filename() {
		return _filename;
	}
	public HCapture renderer(String s) {
		_renderer = s;
		return this;
	}
	public String renderer() {
		return _renderer;
	}
	public void run() {
		if(_isRecording) {
			if(_end < 0) {
				if(H.app().frameCount >= _end) _isRecording = false;
			} else {
				PApplet app = H.app();
			}
			if(!_isRecording) {
			}
		} else {
			if(_start < 0) {
			} else {
			}
			if(_isRecording) {
				H.app().endRecord();
				if(_capturer != null) {
					_capturer.save(_filename);
					_capturer = null;
				}
			}
		}
	}
}
/*
 * HYPE_Processing
 * http:
 * 
 * Copyright (c) 2013 Joshua Davis & James Cruz
 * 
 * Distributed under the BSD License. See LICENSE.txt for details.
 * 
 * All rights reserved.
 */
public static class HColorField implements HColorist {
	private ArrayList<HColorPoint> _colorPoints;
	private float _maxDist;
	private boolean _appliesFill, _appliesStroke, _appliesAlpha;
	public HColorField() {
		this(H.app().width, H.app().height);
	}
	public HColorField(float xBound, float yBound) {
		this( (float) Math.sqrt(xBound*xBound + yBound*yBound) );
	}
	public HColorField(float maximumDistance) {
		_colorPoints = new ArrayList<HColorField.HColorPoint>();
		_maxDist = maximumDistance;
		fillAndStroke();
	}
	public HColorField addPoint(PVector loc, int clr, float radius) {
		return addPoint(loc.x,loc.y, clr, radius);
	}
	public HColorField addPoint(float x, float y, int clr, float radius) {
		HColorPoint pt = new HColorPoint();
		pt.x = x;
		pt.y = y;
		pt.radius = radius;
		pt.clr = clr;
		_colorPoints.add(pt);
		return this;
	}
	public int getColor(float x, float y, int baseColor) {
		int[] baseClrs = HColors.explode(baseColor);
		int[] maxClrs = new int[4];
		int initJ;
		if(_appliesAlpha) {
			initJ = 0;
		} else { 
			initJ = 1;
			maxClrs[0] = baseClrs[0];
		}
		for(int i=0; i<_colorPoints.size(); ++i) {
			HColorPoint pt = _colorPoints.get(i);
			int[] ptClrs = HColors.explode(pt.clr);
			float distLimit = _maxDist * pt.radius;
			float dist = HMath.dist(x,y, pt.x,pt.y);
			if(dist > distLimit)
				dist = distLimit;
			for(int j=initJ; j<4; ++j) {
				int newClrVal = Math.round(
					HMath.map(dist, 0,distLimit, ptClrs[j], baseClrs[j]));
				if(newClrVal > maxClrs[j]) 
					maxClrs[j] = newClrVal;
			}
		}
		return HColors.merge(maxClrs[0],maxClrs[1],maxClrs[2],maxClrs[3]);
	}
	public HColorField appliesAlpha(boolean b) {
		_appliesAlpha = b;
		return this;
	}
	public boolean appliesAlpha() {
		return _appliesAlpha;
	}
	public HColorField fillOnly() {
		_appliesFill = true;
		_appliesStroke = false;
		return this;
	}
	public HColorField strokeOnly() {
		_appliesFill = false;
		_appliesStroke = true;
		return this;
	}
	public HColorField fillAndStroke() {
		_appliesFill = _appliesStroke = true;
		return this;
	}
	public boolean appliesFill() {
		return _appliesFill;
	}
	public boolean appliesStroke() {
		return _appliesStroke;
	}
	public HDrawable applyColor(HDrawable drawable) {
		float x = drawable.x();
		float y = drawable.y();
		if(_appliesFill) {
			int baseFill = drawable.fill();
			drawable.fill( getColor(x,y, baseFill) );
		}
		if(_appliesStroke) {
			int baseStroke = drawable.stroke();
			drawable.stroke( getColor(x,y, baseStroke) );
		}
		return drawable;
	}
	public static class HColorPoint {
		public float x, y, radius;
		public int clr;
	}
}
/*
 * HYPE_Processing
 * http:
 * 
 * Copyright (c) 2013 Joshua Davis & James Cruz
 * 
 * Distributed under the BSD License. See LICENSE.txt for details.
 * 
 * All rights reserved.
 */
public static class HColorPool implements HColorist {
	private ArrayList<Integer> _colorList;
	private boolean _fillFlag, _strokeFlag;
	public HColorPool(int... colors) {
		_colorList = new ArrayList<Integer>();
		for(int i=0; i<colors.length; ++i) add(colors[i]);
		fillAndStroke();
	}
	public HColorPool createCopy() {
		HColorPool copy = new HColorPool();
		copy._fillFlag = _fillFlag;
		copy._strokeFlag = _strokeFlag;
		for(int i=0; i<_colorList.size(); ++i) {
			int clr = _colorList.get(i);
			copy._colorList.add( clr );
		}
		return copy;
	}
	public int size() { 
		return _colorList.size();
	}
	public HColorPool add(int clr) {
		_colorList.add(clr);
		return this;
	}
	public HColorPool add(int clr, int freq) {
		while(freq-- > 0) _colorList.add(clr);
		return this;
	}
	public int getColor() {
		if(_colorList.size() <= 0) return 0;
		int index = (int) Math.floor(H.app().random(_colorList.size()));
		return _colorList.get(index);
	}
	public int getColor(int seed) {
		HMath.tempSeed(seed);
		int clr = getColor();
		HMath.removeTempSeed();
		return clr;
	}
	public HColorPool fillOnly() {
		_fillFlag = true;
		_strokeFlag = false;
		return this;
	}
	public HColorPool strokeOnly() {
		_fillFlag = false;
		_strokeFlag = true;
		return this;
	}
	public HColorPool fillAndStroke() {
		_fillFlag = _strokeFlag = true;
		return this;
	}
	public boolean appliesFill() {
		return _fillFlag;
	}
	public boolean appliesStroke() {
		return _strokeFlag;
	}
	public HDrawable applyColor(HDrawable drawable) {
		if(_fillFlag)
			drawable.fill(getColor());
		if(_strokeFlag)
			drawable.stroke(getColor());
		return drawable;
	}
}
/*
 * HYPE_Processing
 * http:
 * 
 * Copyright (c) 2013 Joshua Davis & James Cruz
 * 
 * Distributed under the BSD License. See LICENSE.txt for details.
 * 
 * All rights reserved.
 */
public static class HColorTransform implements HColorist {
	private float _percA, _percR, _percG, _percB;
	private int _offsetA, _offsetR, _offsetG, _offsetB;
	private boolean fillFlag, strokeFlag;
	public HColorTransform() {
		_percA = _percR = _percG = _percB = 1;
		fillAndStroke();
	}
	public HColorTransform offset(int off) {
		_offsetA = _offsetR = _offsetG = _offsetB = off;
		return this;
	}
	public HColorTransform offset(int r, int g, int b, int a) {
		_offsetA = a;
		_offsetR = r;
		_offsetG = g;
		_offsetB = b;
		return this;
	}
	public HColorTransform offsetA(int a) {
		_offsetA = a;
		return this;
	}
	public int offsetA() {
		return _offsetA;
	}
	public HColorTransform offsetR(int r) {
		_offsetR = r;
		return this;
	}
	public int offsetR() {
		return _offsetR;
	}
	public HColorTransform offsetG(int g) {
		_offsetG = g;
		return this;
	}
	public int offsetG() {
		return _offsetG;
	}
	public HColorTransform offsetB(int b) {
		_offsetB = b;
		return this;
	}
	public int offsetB() {
		return _offsetB;
	}
	public HColorTransform perc(float percentage) {
		_percA = _percR = _percG = _percB = percentage;
		return this;
	}
	public HColorTransform perc(int r, int g, int b, int a) {
		_percA = a;
		_percR = r;
		_percG = g;
		_percB = b;
		return this;
	}
	public HColorTransform percA(float a) {
		_percA = a;
		return this;
	}
	public float percA() {
		return _percA;
	}
	public HColorTransform percR(float r) {
		_percR = r;
		return this;
	}
	public float percR() {
		return _percR;
	}
	public HColorTransform percG(float g) {
		_percG = g;
		return this;
	}
	public float percG() {
		return _percG;
	}
	public HColorTransform percB(float b) {
		_percB = b;
		return this;
	}
	public float percB() {
		return _percB;
	}
	public HColorTransform mergeWith(HColorTransform other) {
		if(other != null) {
			_percA *= other._percA;
			_percR *= other._percR;
			_percG *= other._percG;
			_percB *= other._percB;
			_offsetA += other._offsetA;
			_offsetR += other._offsetR;
			_offsetG += other._offsetG;
			_offsetB += other._offsetB;
		}
		return this;
	}
	public HColorTransform createCopy() {
		HColorTransform copy = new HColorTransform();
		copy._percA = _percA;
		copy._percR = _percR;
		copy._percG = _percG;
		copy._percB = _percB;
		copy._offsetA = _offsetA;
		copy._offsetR = _offsetR;
		copy._offsetG = _offsetG;
		copy._offsetB = _offsetB;
		return copy;
	}
	public HColorTransform createNew(HColorTransform other) {
		return createCopy().mergeWith(other);
	}
	public int getColor(int origColor) {
		int[] clrs = HColors.explode(origColor);
		clrs[0] = Math.round(clrs[0] * _percA) + _offsetA;
		clrs[1] = Math.round(clrs[1] * _percR) + _offsetR;
		clrs[2] = Math.round(clrs[2] * _percG) + _offsetG;
		clrs[3] = Math.round(clrs[3] * _percB) + _offsetB;
		return HColors.merge(clrs[0],clrs[1],clrs[2],clrs[3]);
	}
	public HColorTransform fillOnly() {
		fillFlag = true;
		strokeFlag = false;
		return this;
	}
	public HColorTransform strokeOnly() {
		fillFlag = false;
		strokeFlag = true;
		return this;
	}
	public HColorTransform fillAndStroke() {
		fillFlag = strokeFlag = true;
		return this;
	}
	public boolean appliesFill() {
		return fillFlag;
	}
	public boolean appliesStroke() {
		return strokeFlag;
	}
	public HDrawable applyColor(HDrawable drawable) {
		if(fillFlag) {
			int fill = drawable.fill();
			drawable.fill( getColor(fill) );
		}
		if(strokeFlag) {
			int stroke = drawable.stroke();
			drawable.stroke( getColor(stroke) );
		}
		return drawable;
	}
}
/*
 * HYPE_Processing
 * http:
 * 
 * Copyright (c) 2013 Joshua Davis & James Cruz
 * 
 * Distributed under the BSD License. See LICENSE.txt for details.
 * 
 * All rights reserved.
 */
public static interface HColorist {
	public HColorist fillOnly();
	public HColorist strokeOnly();
	public HColorist fillAndStroke();
	public boolean appliesFill();
	public boolean appliesStroke();
	public HDrawable applyColor(HDrawable drawable);
}
/*
 * HYPE_Processing
 * http:
 * 
 * Copyright (c) 2013 Joshua Davis & James Cruz
 * 
 * Distributed under the BSD License. See LICENSE.txt for details.
 * 
 * All rights reserved.
 */
public static class HColors implements HConstants {
	public static int[] explode(int clr) {
		int[] explodedColors = new int[4];
		for(int i=0; i<4; ++i)
			explodedColors[3-i] = (clr >>> (i*8)) & 0xFF;
		return explodedColors;
	}
	public static int merge(int a, int r, int g, int b) {
		if(a < 0) a = 0;
		else if(a > 255) a = 255;
		if(r < 0) r = 0;
		else if(r > 255) r = 255;
		if(g < 0) g = 0;
		else if(g > 255) g = 255;
		if(b < 0) b = 0;
		else if(b > 255) b = 255;
		return (a<<24) | (r<<16) | (g<<8) | b;
	}
	public static int setAlpha(int clr, int newClr) {
		if(newClr < 0) newClr = 0;
		else if(newClr > 255) newClr = 255;
		return clr & 0x00FFFFFF | (newClr << 24);
	}
	public static int setRed(int clr, int newClr) {
		if(newClr < 0) newClr = 0;
		else if(newClr > 255) newClr = 255;
		return clr & 0xFF00FFFF | (newClr << 16);
	}
	public static int setGreen(int clr, int newClr) {
		if(newClr < 0) newClr = 0;
		else if(newClr > 255) newClr = 255;
		return clr & 0xFFFF00FF | (newClr << 8);
	}
	public static int setBlue(int clr, int newClr) {
		if(newClr < 0) newClr = 0;
		else if(newClr > 255) newClr = 255;
		return clr & 0xFFFFFF00 | newClr;
	}
	public static int getAlpha(int clr) {
		return clr >>> 24;
	}
	public static int getRed(int clr) {
		return (clr >>> 16) & 255;
	}
	public static int getGreen(int clr) {
		return (clr >>> 8) & 255;
	}
	public static int getBlue(int clr) {
		return clr & 255;
	}
	public static boolean isTransparent(int clr) {
		return (clr & 0xFF000000) == 0;
	}
}
/*
 * HYPE_Processing
 * http:
 * 
 * Copyright (c) 2013 Joshua Davis & James Cruz
 * 
 * Distributed under the BSD License. See LICENSE.txt for details.
 * 
 * All rights reserved.
 */
public static interface HConstants {
	public static final int
		NONE = 0,
		LEFT = 1,		
		RIGHT = 2,		
		CENTER_X = 3,	
		TOP = 4,		
		BOTTOM = 8,		
		CENTER_Y = 12,	
		BACK = 16,		
		FRONT = 32,		
		CENTER_Z = 48,	
		CENTER = 63,	
		TOP_LEFT = 5,	
		TOP_RIGHT = 6,	
		BOTTOM_LEFT = 9,	
		BOTTOM_RIGHT = 10,	
		CENTER_LEFT = 13,	
		CENTER_RIGHT = 14,	
		CENTER_TOP = 7,		
		CENTER_BOTTOM = 11,	
		DEFAULT_BACKGROUND_COLOR = 0xFFECF2F5,
		CLEAR	= 0x00FFFFFF,
		WHITE	= 0xFFFFFFFF,
		LGREY	= 0xFFC0C0C0,
		GREY	= 0xFF808080,
		DGREY	= 0xFF404040,
		BLACK	= 0xFF000000,
		RED		= 0xFFFF0000,
		GREEN	= 0xFF00FF00,
		BLUE	= 0xFF0000FF,
		CYAN	= 0xFF00FFFF,
		MAGENTA	= 0xFFFF00FF,
		YELLOW	= 0xFFFFFF00,
		SAW = 0,
		SINE = 1,
		TRIANGLE = 2,
		SQUARE = 3,
		WIDTH = 0,
		HEIGHT = 1,
		SIZE = 2,
		ALPHA = 3,
		X = 4,
		Y = 5,
		Z = 6,
		LOCATION = 7,
		ROTATION = 8,
		DROTATION = 9,
		DX = 10,
		DY = 11,
		DZ = 12,
		DLOC = 13,
		SCALE = 14,
		ROTATIONX = 15,
		ROTATIONY = 16,
		ROTATIONZ = 8,
		DROTATIONX = 17,
		DROTATIONY = 18,
		DROTATIONZ = 9,
		ISOCELES = 0,
		EQUILATERAL = 1,
		ONES = 0xFFFFFFFF,
		ZEROES = 0;
	public static final float
		D2R = PConstants.PI / 180f,
		R2D = 180f / PConstants.PI,
		SQRT2 = 1.4142135623730951f,
		PHI = 1.618033988749895f,
		PHI_1 = 0.618033988749895f,
		TOLERANCE = (float)10e-6f,
		EPSILON = (float)10e-12f;
	public static final HCallback
		NOP = new HCallback() {public void run(Object obj) {}};
}
/*
 * HYPE_Processing
 * http:
 * 
 * Copyright (c) 2013 Joshua Davis & James Cruz
 * 
 * Distributed under the BSD License. See LICENSE.txt for details.
 * 
 * All rights reserved.
 */
public static interface HDirectable extends HLocatable, HRotatable {}
/*
 * HYPE_Processing
 * http:
 * 
 * Copyright (c) 2013 Joshua Davis & James Cruz
 * 
 * Distributed under the BSD License. See LICENSE.txt for details.
 * 
 * All rights reserved.
 */
/**
 * The superclass of all drawables.
 * 
 * Drawables represent objects that are displayable to the stage. TODO
 * 
 * (The stage itself is also technically a drawable, but its usage is different
 * from the other classes.)
 * 
 * @author james
 */
public static abstract class HDrawable extends HNode<HDrawable> implements
	HDirectable,
	HHittable,
	Iterable<HDrawable>
{
	public static final int DEFAULT_FILL = 0xFFFFFFFF;
	public static final int DEFAULT_STROKE = 0xFF000000;
	public static final int DEFAULT_WIDTH = 100;
	public static final int DEFAULT_HEIGHT = 100;
	public static final byte BITMASK_PROPORTIONAL = 1;		
	public static final byte BITMASK_TRANSFORMS_CHILDREN = 2;
	public static final byte BITMASK_STYLES_CHILDREN = 4;	
	public static final byte BITMASK_ROTATES_CHILDREN = 8;	
	/** The parent of this drawable */
	protected HDrawable _parent;
	/** The first child of this drawable */
	protected HDrawable _firstChild;
	/** The last child of this drawable */
	protected HDrawable _lastChild;
	/** The extras bundle of this drawable */
	protected HBundle _extras;
	/** The x location of this drawable */
	protected float _x;
	/** The y location of this drawable */
	protected float _y;
	/** The z location of this drawable */
	protected float _z;
	/** The x anchor percentage of this drawable */
	protected float _anchorU;
	/** The y anchor percentage of this drawable */
	protected float _anchorV;
	/** The width of this drawable */
	protected float _width;
	/** The height of this drawable */
	protected float _height;
	/** The rotation along the x axis of this drawable, in radians */
	protected float _rotationXRad;
	/** The rotation along the y axis of this drawable, in radians */
	protected float _rotationYRad;
	/** The rotation along the z axis of this drawable, in radians */
	protected float _rotationZRad;
	/** The stroke width of this drawable, in pixels */
	protected float _strokeWeight;
	/** The alpha of this drawable, in percentage */
	protected float _alphaPc;
	/** The width-to-height ratio used for proportional resizing */
	/** The number of this drawable's children */
	protected int _numChildren;
	/** The fill color of this drawable */
	protected int _fill;
	/** The stroke color of this drawable */
	protected int _stroke;
	/** The stroke cap of this drawable */
	protected int _strokeCap;
	/** The stroke join of this drawable */
	protected int _strokeJoin;
	/** The bitset that determines proportional resizing, and child transforms */
	protected byte _flags;
	/**
	 * The default constructor for HDrawable.
	 * 
	 * It sets several fields into their proper default values:
	 * - alpha percentage = 1 (100%)
	 * - fill = white
	 * - stroke = black
	 * - stroke cap = round
	 * - stroke join = miter
	 * - stroke weight = 1
	 * - width = 100
	 * - height = 100
	 */
	public HDrawable() {
		_alphaPc = 1;
		_fill = DEFAULT_FILL;
		_stroke = DEFAULT_STROKE;
		_strokeCap = PConstants.ROUND;
		_strokeJoin = PConstants.MITER;
		_strokeWeight = 1;
		_width = DEFAULT_WIDTH;
		_height = DEFAULT_HEIGHT;
	}
	/**
	 * Copies the values of `other`'s basic HDrawable fields.
	 * 
	 * This method is primarily used for implementing createCopy().
	 * It copies the following fields from `other`:
	 * - x & y coordinates
	 * - x & y anchors
	 * - width & height
	 * - rotation
	 * - alpha
	 * - stroke & fill properties
	 * 
	 * @see createCopy()
	 * @param other    The drawable to copy its properties from. 
	 */
	public void copyPropertiesFrom(HDrawable other) {
		_x = other._x;
		_y = other._y;
		_anchorU = other._anchorU;
		_anchorV = other._anchorV;
		_width = other._width;
		_height = other._height;
		_rotationZRad = other._rotationZRad;
		_alphaPc = other._alphaPc;
		_strokeWeight = other._strokeWeight;
		_fill = other._fill;
		_stroke = other._stroke;
		_strokeCap = other._strokeCap;
		_strokeJoin = other._strokeJoin;
	}
	/**
	 * Creates a copy of this drawable.
	 * 
	 * This method is abstract and is meant to be implemented by the children
	 * of this class.
	 * 
	 * @return A copy of this drawable.
	 */
	public abstract HDrawable createCopy();
	public boolean invalidChild(HDrawable destParent) {
		if(destParent == null) return true;
		if(destParent.equals(this)) return true;
		return false;
	}
	private boolean invalidDest(HDrawable dest, String warnLoc) {
		String warnType;
		String warnMsg;
		if( dest == null ) {
			warnType = "Null Destination";
			warnMsg = HWarnings.NULL_ARGUMENT;
		} else if( dest._parent == null ) {
			warnType = "Invalid Destination";
			warnMsg = HWarnings.INVALID_DEST;
		} else if( dest._parent.equals(this) ) {
			warnType = "Recursive Child";
			warnMsg = HWarnings.CHILDCEPTION;
		} else if( dest.equals(this) ) {
			warnType = "Invalid Destination";
			warnMsg = HWarnings.DESTCEPTION;
		} else return false;
		HWarnings.warn(warnType, warnLoc, warnMsg);
		return true;
	}
	public boolean poppedOut() {
		return (_parent == null);
	}
	public void popOut() {
		if(_parent == null) return;
		if(_prev == null) _parent._firstChild = _next;
		if(_next == null) _parent._lastChild = _prev;
		--_parent._numChildren;
		_parent = null;
		super.popOut();
	}
	public void putBefore(HDrawable dest) {
		if(invalidDest(dest,"HDrawable.putBefore()")) return;
		popOut();
		super.putBefore(dest);
		_parent = dest._parent;
		if(_prev == null) _parent._firstChild = this;
		++_parent._numChildren;
	}
	public void putAfter(HDrawable dest) {
		if(invalidDest(dest,"HDrawable.putAfter()")) return;
		popOut();
		super.putAfter(dest);
		_parent = dest._parent;
		if(_next == null) _parent._lastChild = this;
		++_parent._numChildren;
	}
	public void swapLeft() {
		boolean isLast = (_next == null);
		super.swapLeft();
		if(_prev == null) _parent._firstChild = this;
		if(_next != null && isLast) _parent._lastChild = _next;
	}
	public void swapRight() {
		boolean isFirst = (_prev == null);
		super.swapRight();
		if(_next == null) _parent._lastChild = this;
		if(_prev != null && isFirst) _parent._firstChild = _prev;
	}
	public void replaceNode(HDrawable dest) {
		if(invalidDest(dest,"HDrawable.replaceNode()")) return;
		super.replaceNode(dest);
		_parent = dest._parent;
		dest._parent = null;
		if(_prev == null) _parent._firstChild = this;
		if(_next == null) _parent._lastChild = this;
	}
	/**
	 * Returns the parent of this drawable.
	 * 
	 * @return The parent of this drawable, or null if there's none.
	 */
	public HDrawable parent() {
		return _parent;
	}
	/**
	 * Returns the first child of this drawable.
	 * 
	 * If this drawable has only one child, then the first child is also
	 * considered as the last child.
	 * 
	 * @return The first child of this drawable, or null if there's none.
	 */
	public HDrawable firstChild() {
		return _firstChild;
	}
	/**
	 * Returns the last child of this drawable.
	 * 
	 * If this drawable has only one child, then the last child is also
	 * considered as the first child.
	 * 
	 * @return The last child of this drawable, or null if there's none.
	 */
	public HDrawable lastChild() {
		return _lastChild;
	}
	/**
	 * Checks if the given drawable is the parent of this drawable.
	 * 
	 * @param d    The drawable to be checked
	 * @return True if this drawable is the parent of `d`
	 */
	public boolean parentOf(HDrawable d) {
		return (d != null) && (d._parent != null) && (d._parent.equals(this));
	}
	/**
	 * Returns the number of children of this drawable.
	 * 
	 * @return The number of children of this drawable.
	 */
	public int numChildren() {
		return _numChildren;
	}
	public HCanvas add(HCanvas child) {
		add((HDrawable) child);
		return child;
	}
	public HEllipse add(HEllipse child) {
		add((HDrawable) child);
		return child;
	}
	public HGroup add(HGroup child) {
		add((HDrawable) child);
		return child;
	}
	public HImage add(HImage child) {
		add((HDrawable) child);
		return child;
	}
	public HPath add(HPath child) {
		add((HDrawable) child);
		return child;
	}
	public HRect add(HRect child) {
		add((HDrawable) child);
		return child;
	}
	public HShape add(HShape child) {
		add((HDrawable) child);
		return child;
	}
	public HText add(HText child) {
		add((HDrawable) child);
		return child;
	}
	/**
	 * Adds the passed drawable as this drawable's child.
	 * 
	 * If `child` is already a child of another drawable, it removes itself from
	 * its current parent and gets added to this drawable.
	 * 
	 * If `child` is already a child of this drawable, then it does nothing.
	 * 
	 * @param child    The child to be added to this drawable.
	 * @return The drawable passed through this method.
	 */
	public HDrawable add(HDrawable child) {
		if(child == null) {
			HWarnings.warn("An Empty Child", "HDrawable.add()",
					HWarnings.NULL_ARGUMENT);
		} else if( child.invalidChild(this) ) {
			HWarnings.warn("Invalid Child", "HDrawable.add()",
					HWarnings.INVALID_CHILD);
		} else if( !parentOf(child) ) {
			if(_lastChild == null) {
				_firstChild = _lastChild = child;
				child.popOut();
				child._parent = this;
				++_numChildren;
			} else child.putAfter(_lastChild);
		}
		return child;
	}
	public HCanvas remove(HCanvas child) {
		remove((HDrawable) child);
		return child;
	}
	public HEllipse remove(HEllipse child) {
		remove((HDrawable) child);
		return child;
	}
	public HGroup remove(HGroup child) {
		remove((HDrawable) child);
		return child;
	}
	public HImage remove(HImage child) {
		remove((HDrawable) child);
		return child;
	}
	public HPath remove(HPath child) {
		remove((HDrawable) child);
		return child;
	}
	public HRect remove(HRect child) {
		remove((HDrawable) child);
		return child;
	}
	public HShape remove(HShape child) {
		remove((HDrawable) child);
		return child;
	}
	public HText remove(HText child) {
		remove((HDrawable) child);
		return child;
	}
	/**
	 * Removes a child from this drawable.
	 * 
	 * If `child` isn't a child of this drawable, this method will do nothing.
	 * Regardless, it will still return `child`.
	 * 
	 * @param child    The child to be removed from this drawable
	 * @return The drawable passed through this method.
	 */
	public HDrawable remove(HDrawable child) {
		if( parentOf(child) ) child.popOut();
		else HWarnings.warn("Not a Child", "HDrawable.remove()", null);
		return child;
	}
	/**
	 * Creates a new iterator for this drawable to iterate through its children.
	 * 
	 * Note that while HIterator has similar functionalities with
	 * java.util.Iterator, the former does _not_ extend the latter. This is due
	 * to js mode compatibility issues.
	 * 
	 * @see HDrawableIterator
	 * @see HIterator
	 * @return A new HIterator for this drawable
	 */
	public HDrawableIterator iterator() {
		return new HDrawableIterator(this);
	}
	/**
	 * Sets the x and y position of this drawable.
	 * 
	 * @chainable
	 * @param newX    The new x coordinate for this drawable.
	 * @param newY    The new y coordinate for this drawable.
	 * @return This drawable.
	 */
	public HDrawable loc(float newX, float newY) {
		_x = newX;
		_y = newY;
		return this;
	}
	/**
	 * Sets the x, y and z position of this drawable.
	 * 
	 * @chainable
	 * @param newX    The new x coordinate for this drawable. 
	 * @param newY    The new y coordinate for this drawable.
	 * @param newZ    The new z coordinate for this drawable.
	 * @return This drawable.
	 */
	public HDrawable loc(float newX, float newY, float newZ) {
		_x = newX;
		_y = newY;
		_z = newZ;
		return this;
	}
	/**
	 * Sets the position of this drawable via PVector.
	 * 
	 * @chainable
	 * @param pt    A PVector containing the new coordinates for this drawable.
	 * @return This drawable.
	 */
	public HDrawable loc(PVector pt) {
		_x = pt.x;
		_y = pt.y;
		_z = pt.z;
		return this;
	}
	/**
	 * Returns the position of this drawable as a PVector.
	 * 
	 * @return A new PVector containing the coordinates of this drawable.
	 */
	public PVector loc() {
		return new PVector(_x,_y,_z);
	}
	public HDrawable x(float newX) {
		_x = newX;
		return this;
	}
	public float x() {
		return _x;
	}
	public HDrawable y(float newY) {
		_y = newY;
		return this;
	}
	public float y() {
		return _y;
	}
	public HDrawable z(float newZ) {
		_z = newZ;
		return this;
	}
	public float z() {
		return _z;
	}
	/**
	 * Moves this drawable from its original x & y position.
	 * 
	 * @chainable
	 * @param dx    The amount this drawable will be moved on the x-axis.
	 * @param dy    The amount this drawable will be moved on the y-axis.
	 * @return This drawable.
	 */
	public HDrawable move(float dx, float dy) {
		_x += dx;
		_y += dy;
		return this;
	}
	/**
	 * Moves this drawable from its original x, y & z position.
	 * 
	 * @chainable
	 * @param dx    The amount this drawable will be moved on the x-axis.
	 * @param dy    The amount this drawable will be moved on the y-axis.
	 * @param dz    The amount this drawable will be moved on the z-axis.
	 * @return This drawable.
	 */
	public HDrawable move(float dx, float dy, float dz) {
		_x += dx;
		_y += dy;
		_z += dz;
		return this;
	}
	/**
	 * Positions this drawable at the defined location in relation to its
	 * parent, or (0,0) if the parent is null.
	 * 
	 * The `where` parameter can be any of the following HConstants values:
	 * - `HConstants.NONE` (does nothing)
	 * - `HConstants.LEFT`
	 * - `HConstants.RIGHT`
	 * - `HConstants.CENTER_X`
	 * - `HConstants.TOP`
	 * - `HConstants.BOTTOM`
	 * - `HConstants.CENTER_Y`
	 * - `HConstants.CENTER`
	 * - `HConstants.TOP_LEFT`
	 * - `HConstants.TOP_RIGHT`
	 * - `HConstants.BOTTOM_LEFT`
	 * - `HConstants.BOTTOM_RIGHT`
	 * - `HConstants.CENTER_LEFT`
	 * - `HConstants.CENTER_RIGHT`
	 * - `HConstants.CENTER_TOP`
	 * - `HConstants.CENTER_BOTTOM`
	 * 
	 * These values can be combined via bitwise OR, so `H.TOP | H.LEFT` would be
	 * equal to `H.TOP_LEFT`.
	 * 
	 * @chainable
	 * @see anchorAt(int)
	 * @param where    The value that represents the location for this drawable.
	 * @return This drawable.
	 */
	public HDrawable locAt(int where) {
		if(_parent!=null) {
			if(HMath.hasBits(where, HConstants.CENTER_X)) {
				_x = _parent.width()/2 - _parent.anchorX();
			} else if(HMath.hasBits(where, HConstants.LEFT)) {
				_x = -_parent.anchorX();
			} else if(HMath.hasBits(where, HConstants.RIGHT)) {
				_x = _parent.width() - _parent.anchorX();
			}
			if(HMath.hasBits(where, HConstants.CENTER_Y)) {
				_y = _parent.height()/2 - _parent.anchorY();
			} else if(HMath.hasBits(where, HConstants.TOP)) {
				_y = -_parent.anchorY();
			} else if(HMath.hasBits(where, HConstants.BOTTOM)) {
				_y = _parent.height() - _parent.anchorY();
			}
		}
		return this;
	}
	/**
	 * Sets the anchor of this drawable by pixels.
	 * 
	 * Note that HDrawable stores its anchor coordinates as a percentage of its
	 * width and height. So if the current size of this drawable is `(100,100)`,
	 * setting the anchor to `(75,75)` will be stored as `(0.75,0.75)`.
	 * 
	 * In case that the current width or height is 0, then the width or height
	 * is assumed to be 100 when computing the anchor in this method.
	 * 
	 * @chainable
	 * @see anchor(PVector)
	 * @see anchorX(float)
	 * @see anchorY(float)
	 * @param pxX    The desired x anchor for this drawable, in pixels.
	 * @param pxY    The desired y anchor for this drawable, in pixels.
	 * @return This drawable.
	 */
	public HDrawable anchor(float pxX, float pxY) {
		return anchorX(pxX).anchorY(pxY);
	}
	/**
	 * Sets the anchor of this drawable by pixels via a PVector.
	 * 
	 * This method calls anchor(float,float) with `pt`'s x and y fields as the
	 * arguments.
	 * 
	 * @chainable
	 * @see anchor(float,float)
	 * @param pt    The PVector containing the desired x and y anchor for this drawable, in pixels
	 * @return This drawable.
	 */
	public HDrawable anchor(PVector pt) {
		return anchor(pt.x, pt.y);
	}
	/**
	 * Returns the anchor of this drawable in pixels.
	 * 
	 * The result of this method is the product of its width & height and its
	 * x & y anchor percentages respectively. So if this drawable is anchored at
	 * the center, this method will return `(50,50)` when the size is
	 * `(100,100)`, `(30,30)` when the size is `(60,60)` and `(0,0)` when size
	 * is `(0,0)`.
	 * 
	 * @see anchorX()
	 * @see anchorY()
	 * @return A new PVector containing the anchor of this drawable, in pixels.
	 */
	public PVector anchor() {
		return new PVector( anchorX(), anchorY() );
	}
	/**
	 * Sets the x anchor of this drawable by pixels.
	 * 
	 * Note that HDrawable stores its x anchor coordinates as a percentage of
	 * its width. If the width of this drawable is 0, it is assumed as 100 when
	 * computing the x anchor in this method.
	 * 
	 * @chainable
	 * @see anchor(float,float)
	 * @see anchor(PVector)
	 * @see anchorY(float)
	 * @param pxX    The desired x anchor for this drawable, in pixels.
	 * @return This drawable.
	 */
	public HDrawable anchorX(float pxX) {
		_anchorU = pxX / (_width==0? 100 : _width);
		return this;
	}
	/**
	 * Returns the x anchor of this drawable in pixels.
	 * 
	 * The result of this method is the product of its width and x anchor
	 * percentage.
	 * 
	 * @see anchor()
	 * @see anchorY()
	 * @return The x anchor of this drawable, in pixels.
	 */
	public float anchorX() {
		return _width * _anchorU;
	}
	/**
	 * Sets the y anchor of this drawable by pixels.
	 * 
	 * Note that HDrawable stores its y anchor coordinates as a percentage of
	 * its height. If the height of this drawable is 0, it is assumed as 100
	 * when computing the y anchor in this method
	 * 
	 * @chainable
	 * @see anchor(float,float)
	 * @see anchor(PVector)
	 * @see anchorX(float)
	 * @param pxY    The desired y anchor for this drawable, in pixels.
	 * @return This drawable.
	 */
	public HDrawable anchorY(float pxY) {
		_anchorV = pxY / (_height==0? 100 : _height);
		return this;
	}
	/**
	 * Returns the y anchor of this drawable in pixels.
	 * 
	 * The result of this method is the product of its height and y anchor
	 * percentage.
	 * 
	 * @see anchor()
	 * @see anchorX()
	 * @return The y anchor of this drawable, in pixels.
	 */
	public float anchorY() {
		return _height * _anchorV;
	}
	/**
	 * Sets the anchor of this drawable in UV coordinates.
	 * 
	 * @chainable
	 * @see anchorU(float)
	 * @see anchorV(float)
	 * @param u    The desired `u` coordinate for this drawable's anchor.
	 * @param v    The desired `v` coordinate for this drawable's anchor.
	 * @return This drawable.
	 */
	public HDrawable anchorUV(float u, float v) {
		return anchorU(u).anchorV(v);
	}
	/**
	 * Returns the UV coordinates of this drawable's anchor.
	 * 
	 * @see anchorU()
	 * @see anchorV()
	 * @return A new PVector containing this drawable's anchor as percentage
	 */
	public PVector anchorUV() {
		return new PVector(_anchorU, _anchorV);
	}
	/**
	 * Sets the `u` coordinate for this drawable's anchor.
	 * 
	 * @chainable
	 * @see anchorUV(float,float)
	 * @see anchorV(float)
	 * @param u    The desired `u` coordinate for this drawable's anchor.
	 * @return This drawable.
	 */
	public HDrawable anchorU(float u) {
		_anchorU = u;
		return this;
	}
	/**
	 * Returns the `u` coordinate of this drawable's anchor.
	 * 
	 * @see anchorUV(), anchorV()
	 * @return The `u` coordinate of this drawable's anchor.
	 */
	public float anchorU() {
		return _anchorU;
	}
	/**
	 * Sets the `u` coordinate for this drawable's anchor.
	 * 
	 * @chainable
	 * @see anchorUV()
	 * @see anchorU()
	 * @param v    The desired `v` coordinate for this drawable's anchor.
	 * @return This drawable.
	 */
	public HDrawable anchorV(float v) {
		_anchorV = v;
		return this;
	}
	/**
	 * Returns the `v` coordinate of this draable's anchor.
	 * 
	 * @see anchorUV()
	 * @see anchorU()
	 * @return The y anchor of this drawable.
	 */
	public float anchorV() {
		return _anchorV;
	}
	/**
	 * Sets the anchor of this drawable at the defined location in relation to
	 * itself.
	 * 
	 * The `where` parameter can be any of the following HConstants values:
	 * - `HConstants.NONE` (does nothing)
	 * - `HConstants.LEFT`
	 * - `HConstants.RIGHT`
	 * - `HConstants.CENTER_X`
	 * - `HConstants.TOP`
	 * - `HConstants.BOTTOM`
	 * - `HConstants.CENTER_Y`
	 * - `HConstants.CENTER`
	 * - `HConstants.TOP_LEFT`
	 * - `HConstants.TOP_RIGHT`
	 * - `HConstants.BOTTOM_LEFT`
	 * - `HConstants.BOTTOM_RIGHT`
	 * - `HConstants.CENTER_LEFT`
	 * - `HConstants.CENTER_RIGHT`
	 * - `HConstants.CENTER_TOP`
	 * - `HConstants.CENTER_BOTTOM`
	 * 
	 * These values can be combined via bitwise OR, so `H.TOP | H.LEFT` would be
	 * equal to `H.TOP_LEFT`.
	 * 
	 * @chainable
	 * @see locAt(int)
	 * @param where    The value that represents the anchor for this drawable.
	 * @return This drawable.
	 */
	public HDrawable anchorAt(int where) {
		if(HMath.hasBits(where, HConstants.CENTER_X))
			_anchorU = 0.5f;
		else if(HMath.hasBits(where, HConstants.LEFT))
			_anchorU = 0;
		else if(HMath.hasBits(where, HConstants.RIGHT))
			_anchorU = 1;
		if(HMath.hasBits(where, HConstants.CENTER_Y))
			_anchorV = 0.5f;
		else if(HMath.hasBits(where, HConstants.TOP))
			_anchorV = 0;
		else if(HMath.hasBits(where, HConstants.BOTTOM))
			_anchorV = 1;
		return this;
	}
	/**
	 * Sets the size of this drawable.
	 * 
	 * If this drawable is set to resize proportionally, the new width will be
	 * adjusted according to the new height by default. (The exception to this
	 * is if the new height is the same as the old height, then the new height
	 * will be adjusted according to the new width instead.)
	 * 
	 * @chainable
	 * @see size(float)
	 * @see width(float)
	 * @see height(float)
	 * @param w    The new width for this drawable.
	 * @param h    The new height for this drawable.
	 * @return This drawable.
	 */
	public HDrawable size(float w, float h) {
		onResize( _width, _height, _width=w, _height=h );
		return this;
	}
	/**
	 * Sets the size of this drawable.
	 * 
	 * This method delegates size(float,float).
	 * 
	 * @chainable
	 * @see size(float,float)
	 * @see width(float)
	 * @see height(float)
	 * @param s    The new width and height for this drawable.
	 * @return This drawable.
	 */
	public HDrawable size(float s) {
		return size(s,s);
	}
	public HDrawable size(PVector s) {
		return size(s.x,s.y);
	}
	/**
	 * Returns the size of this drawable.
	 * 
	 * @see width()
	 * @see height()
	 * @return A new PVector containing the width and height of this drawable.
	 */
	public PVector size() {
		return new PVector(_width,_height);
	}
	/**
	 * Sets the width of this drawable.
	 * 
	 * If this drawable is set to resize proportionally, the height will also
	 * change accordingly via onResize(float,float,float,float).
	 * 
	 * @chainable
	 * @see size(float,float)
	 * @see size(float)
	 * @see height(float)
	 * @param w    The new width for this drawable
	 * @return This drawable.
	 */
	public HDrawable width(float w) {
		onResize( _width, _height, _width=w, _height );
		return this;
	}
	/**
	 * Returns the width of this drawable.
	 * 
	 * @see size()
	 * @see height()
	 * @return This drawable's width.
	 */
	public float width() {
		return _width;
	}
	/**
	 * Sets the height of this drawable.
	 * 
	 * If this drawable is set to resize proportionally, the width will also
	 * change accordingly.
	 * 
	 * @chainable
	 * @see size(float,float)
	 * @see size(float)
	 * @see width(float)
	 * @param h    The new height for this drawable
	 * @return This drawable.
	 */
	public HDrawable height(float h) {
		onResize( _width, _height, _width, _height=h );
		return this;
	}
	/**
	 * Returns the height of this drawable.
	 * 
	 * @see size()
	 * @see width()
	 * @return This drawable's height
	 */
	public float height() {
		return _height;
	}
	/**
	 * Multiplies the width and height of this drawable by the given scale
	 * factor.
	 * 
	 * This method delegates size(float,float).
	 * 
	 * @chainable
	 * @see scale(float,float)
	 * @param s    The scale factor for the width and height.
	 * @return This drawable.
	 */
	public HDrawable scale(float s) {
		return size(_width*s, _height*s);
	}
	/**
	 * Multiplies the width and height of this drawable by the given scale
	 * factors.
	 * 
	 * This method delegates size(float,float).
	 * 
	 * @chainable
	 * @see scale(float)
	 * @param sw    The scale factor for the width.
	 * @param sh    The scale factor for the height.
	 * @return This drawable.
	 */
	public HDrawable scale(float sw, float sh) {
		return size(_width*sw, _height*sh);
	}
	/**
	 * Sets whether or not this drawable will be resized proportionally.
	 * 
	 * If set to true, then this drawable will try to keep the width and height
	 * at the same ratio when you try to change either the width or height.
	 * 
	 * @chainable
	 * @param b    True, if this drawable will keep its size proportionally.
	 * @return This drawable.
	 */
	public HDrawable proportional(boolean b) {
		_flags = HMath.setBits(_flags, BITMASK_PROPORTIONAL, b);
		return this;
	}
	/**
	 * Returns whether or not this drawable is in proportional mode.
	 * 
	 * @return True if this drawable is in proportional mode, else false.
	 */
	public boolean proportional() {
		return HMath.hasBits(_flags,BITMASK_PROPORTIONAL);
	}
	public HDrawable transformsChildren(boolean b) {
		_flags = HMath.setBits(_flags,BITMASK_TRANSFORMS_CHILDREN,b);
		return this;
	}
	public boolean transformsChildren() {
		return HMath.hasBits(_flags,BITMASK_TRANSFORMS_CHILDREN);
	}
	/**
	 * Called after the size of this drawable is changed.
	 * 
	 * By default, this method handles proportional resizing, child
	 * transformations.
	 * 
	 * If you subclass HDrawable and want to execute code whenever your custom
	 * class changes size, override this method. But remember to call
	 * `super.onResize()` at the start of your override when you want to keep
	 * proportional resizing and child transforms.
	 * 
	 * @param oldW    The old width before being resize.
	 * @param oldH    The old height before being resize.
	 * @param newW    The new width after being resized.
	 * @param newH    The new height after being resized.
	 */
	protected void onResize(float oldW, float oldH, float newW, float newH) {
		if(proportional()) {
			if(newH != oldH) {
				if(oldH != 0) _width = oldW * newH/oldH;
			} else if(newW != oldW) {
				if(oldW != 0) _height = oldH * newW/oldW;
			}
		}
		if(transformsChildren()) {
			float scalew = (oldW==0)? 1 : _width/oldW;
			float scaleh = (oldH==0)? 1 : _height/oldH;
			HDrawable child = _firstChild;
			while(child != null) {
				child.loc(child._x*scalew, child._y*scaleh);
				child.scale(scalew,scaleh);
				child = child._next;
			}
		}
	}
	public void bounds(PVector boundsLoc, PVector boundsSize) {
		float[] vals = new float[4];
		bounds(vals);
		boundsLoc.x = vals[0];
		boundsLoc.y = vals[1];
		boundsSize.x = vals[2];
		boundsSize.y = vals[3];
	}
	public void bounds(float[] boundsValues) {
		float x1 = -anchorX(), y1 = -anchorY();
		float x2 = x1+_width,  y2 = y1+_height;
		float minx, miny, maxx, maxy;
		float[] tl = HMath.absLocArr(this, x1, y1);
		minx = maxx = tl[0];
		miny = maxy = tl[1];
		float[] tr = HMath.absLocArr(this, x2, y1);
		if(tr[0]<minx) minx=tr[0]; else if(tr[0]>maxx) maxx=tr[0];
		if(tr[1]<miny) miny=tr[1]; else if(tr[1]>maxy) maxy=tr[1];
		float[] bl = HMath.absLocArr(this, x1, y2);
		if(bl[0]<minx) minx=bl[0]; else if(bl[0]>maxx) maxx=bl[0];
		if(bl[1]<miny) miny=bl[1]; else if(bl[1]>maxy) maxy=bl[1];
		float[] br = HMath.absLocArr(this, x2, y2);
		if(br[0]<minx) minx=br[0]; else if(br[0]>maxx) maxx=br[0];
		if(br[1]<miny) miny=br[1]; else if(br[1]>maxy) maxy=br[1];
		boundsValues[0] = minx;
		boundsValues[1] = miny;
		boundsValues[2] = maxx - minx;
		boundsValues[3] = maxy - miny;
	}
	/**
	 * Returns the bounding size of this drawable based on this drawble's
	 * rotation.
	 * 
	 * @deprecated This will be replaced with boundingBox() in the future.
	 * @return The bounding size of this drawable.
	 */
	public PVector boundingSize() {
		float cosVal = (float)Math.cos(_rotationZRad);
		float sinVal = (float)Math.sin(_rotationZRad);
		float drawX = -anchorX();
		float drawY = -anchorY();
		float x1 = drawX;			
		float x2 = _width + drawX;	
		float y1 = drawY;			
		float y2 = _height + drawY;	
		float[] xCoords = new float[4];
		float[] yCoords = new float[4];
		xCoords[0] = x1*cosVal + y1*sinVal;
		yCoords[0] = x1*sinVal + y1*cosVal;
		xCoords[1] = x2*cosVal + y1*sinVal;
		yCoords[1] = x2*sinVal + y1*cosVal;
		xCoords[2] = x1*cosVal + y2*sinVal;
		yCoords[2] = x1*sinVal + y2*cosVal;
		xCoords[3] = x2*cosVal + y2*sinVal;
		yCoords[3] = x2*sinVal + y2*cosVal;
		float minX = xCoords[3];
		float maxX = minX;
		float minY = yCoords[3];
		float maxY = maxX;
		for(int i=0; i<3; ++i) {
			float x = xCoords[i];
			float y = yCoords[i];
			if(x < minX) minX = x;
			else if(x > maxX) maxX = x;
			if(y < minY) minY = y;
			else if(y > maxY) maxY = y;
		}
		return new PVector(maxX-minX, maxY-minY);
	}
	/**
	 * Sets the fill for this drawable.
	 * 
	 * This method takes in a standard Processing color integer, which are
	 * commonly represented as `0xAARRGGBB` or `#RRGGBB`.
	 * 
	 * @chainable
	 * @see fill(int,int)
	 * @see fill(int,int,int)
	 * @see fill(int,int,int,int)
	 * @param clr    The new fill for this drawable.
	 * @return This drawable.
	 */
	public HDrawable fill(int clr) {
		if(0 <= clr && clr <= 255) clr |= clr<<8 | clr<<16 | 0xFF000000;
		_fill = clr;
		onStyleChange();
		return this;
	}
	/**
	 * Sets the fill for this drawable.
	 * 
	 * The `clr` parameter is a standard Processing color integer and alpha is
	 * any number from 0 to 255.
	 * 
	 * Note that `alpha` would override the existing alpha value of `clr`. So if
	 * `clr` is `0x80FFFFFF` and `alpha` is `0x40`, the fill will become
	 * `0x40FFFFFF`.
	 * 
	 * @chainable
	 * @see fill(int)
	 * @see fill(int,int,int)
	 * @see fill(int,int,int,int)
	 * @see noFill()
	 * @param clr    The new fill for this drawable, excluding its alpha value.
	 * @param alpha  The alpha for the new fill for this drawable.
	 * @return This drawable.
	 */
	public HDrawable fill(int clr, int alpha) {
		if(0 <= clr && clr <= 255) clr |= clr<<8 | clr<<16;
		_fill = HColors.setAlpha(clr,alpha);
		onStyleChange();
		return this;
	}
	/**
	 * Sets the fill for this drawable.
	 * 
	 * The parameters `r`, `g` and `b` can be any number from 0 to 255.
	 * 
	 * @chainable
	 * @see fill(int)
	 * @see fill(int,int)
	 * @see fill(int,int,int,int)
	 * @see noFill()
	 * @param r    The red component for the new fill for this drawable
	 * @param g    The green component for the new fill for this drawable
	 * @param b    The blue component for the new fill for this drawable
	 * @return This drawable.
	 */
	public HDrawable fill(int r, int g, int b) {
		_fill = HColors.merge(255,r,g,b);
		onStyleChange();
		return this;
	}
	/**
	 * Sets the fill for this drawable.
	 * 
	 * The parameters `r`, `g`, `b` and `a` can be any number from 0 to 255.
	 * 
	 * @chainable
	 * @see fill(int)
	 * @see fill(int,int)
	 * @see fill(int,int,int)
	 * @see noFill()
	 * @param r    The red component for the new fill for this drawable
	 * @param g    The green component for the new fill for this drawable
	 * @param b    The blue component for the new fill for this drawable
	 * @param a    The alpha component for the new fill for this drawable
	 * @return This drawable.
	 */
	public HDrawable fill(int r, int g, int b, int a) {
		_fill = HColors.merge(a,r,g,b);
		onStyleChange();
		return this;
	}
	/**
	 * Returns the fill for this drawable.
	 * 
	 * The color returned by this method is a standard Processing color integer.
	 * 
	 * @return The fill of this drawable.
	 */
	public int fill() {
		return _fill;
	}
	/**
	 * Removes the fill of this drawable.
	 * 
	 * Technically, this method sets the fill to HConstants.CLEAR, which is
	 * `0x00FFFFFF` or fully transparent white.
	 * 
	 * @chainable
	 * @see fill(int)
	 * @see fill(int,int)
	 * @see fill(int,int,int)
	 * @see fill(int,int,int,int)
	 * @return This drawable.
	 */
	public HDrawable noFill() {
		return fill(HConstants.CLEAR);
	}
	/**
	 * Sets the stroke color for this drawable.
	 * 
	 * This method takes in a standard Processing color integer, which are
	 * commonly represented as `0xAARRGGBB` or `#RRGGBB`.
	 * 
	 * @chainable
	 * @see stroke(int,int)
	 * @see stroke(int,int,int)
	 * @see stroke(int,int,int,int)
	 * @see noStroke()
	 * @param clr    The new stroke color for this drawable.
	 * @return This drawable
	 */
	public HDrawable stroke(int clr) {
		if(0 <= clr && clr <= 255) clr |= clr<<8 | clr<<16 | 0xFF000000;
		_stroke = clr;
		onStyleChange();
		return this;
	}
	/**
	 * Sets the stroke color for this drawable.
	 * 
	 * The `clr` parameter is a standard Processing color integer and alpha is
	 * any number from 0 to 255.
	 * 
	 * Note that `alpha` would override the existing alpha value of `clr`. So if
	 * `clr` is `0x80FFFFFF` and `alpha` is `0x40`, the fill will become
	 * `0x40FFFFFF`.
	 * 
	 * @chainable
	 * @see stroke(int)
	 * @see stroke(int,int,int)
	 * @see stroke(int,int,int,int)
	 * @see noStroke()
	 * @param clr    The new stroke color for this drawable, excluding its alpha value.
	 * @param alpha  The alpha for the new stroke color for this drawable.
	 * @return This drawable.
	 */
	public HDrawable stroke(int clr, int alpha) {
		if(0 <= clr && clr <= 255) clr |= clr<<8 | clr<<16;
		_stroke = HColors.setAlpha(clr,alpha);
		onStyleChange();
		return this;
	}
	/**
	 * Sets the stroke color for this drawable.
	 * 
	 * The parameters `r`, `g` and `b` can be any number from 0 to 255.
	 * 
	 * @chainable
	 * @see stroke(int)
	 * @see stroke(int,int)
	 * @see stroke(int,int,int,int)
	 * @see noStroke()
	 * @param r    The red component for the new stroke color for this drawable
	 * @param g    The green component for the new stroke color for this drawable
	 * @param b    The blue component for the new stroke color for this drawable
	 * @return This drawable.
	 */
	public HDrawable stroke(int r, int g, int b) {
		_stroke = HColors.merge(255,r,g,b);
		onStyleChange();
		return this;
	}
	/**
	 * Sets the stroke color for this drawable.
	 * 
	 * The parameters `r`, `g` `b` and `a` can be any number from 0 to 255.
	 * 
	 * @chainable
	 * @see stroke(int)
	 * @see stroke(int,int)
	 * @see stroke(int,int,int)
	 * @see noStroke()
	 * @param r    The red component for the new stroke color for this drawable
	 * @param g    The green component for the new stroke color for this drawable
	 * @param b    The blue component for the new stroke color for this drawable
	 * @param a    The alpha component for the new stroke color for this drawable
	 * @return This drawable.
	 */
	public HDrawable stroke(int r, int g, int b, int a) {
		_stroke = HColors.merge(a,r,g,b);
		onStyleChange();
		return this;
	}
	/**
	 * Returns the stroke color for this drawable.
	 * 
	 * The color returned by this method is a standard Processing color integer.
	 * 
	 * @return The stroke color of this drawable.
	 */
	public int stroke() {
		return _stroke;
	}
	/**
	 * Removes the stroke color of this drawable.
	 * 
	 * Technically, this method sets the stroke color to HConstants.CLEAR, which
	 * is equalt to `0x00FFFFFF` or fully transparent white.
	 * 
	 * @chainable
	 * @see stroke(int)
	 * @see stroke(int,int)
	 * @see stroke(int,int,int)
	 * @see stroke(int,int,int,int)
	 * @return This drawable.
	 */
	public HDrawable noStroke() {
		return stroke(HConstants.CLEAR);
	}
	/**
	 * Sets the stroke cap for this drawable.
	 * 
	 * The stroke cap can be any of the following values:
	 * - `PConstants.SQUARE`
	 * - `PConstants.PROJECT`
	 * - `PConstants.ROUND`
	 * 
	 * @chainable
	 * @see Processing::strokeCap()
	 * @param type    The stroke cap type for this drawable.
	 * @return This drawable.
	 */
	public HDrawable strokeCap(int type) {
		_strokeCap = type;
		onStyleChange();
		return this;
	}
	/**
	 * Returns the stroke cap for this drawable.
	 * 
	 * @return The integer representing the stroke cap of this drawable.
	 */
	public int strokeCap() {
		return _strokeCap;
	}
	/**
	 * Sets the stroke join for this drawable.
	 * 
	 * The stroke join can be any of the following values:
	 * - `PConstants.MITER`
	 * - `PConstants.BEVEL`
	 * - `PConstants.ROUND`
	 * 
	 * @chainable
	 * @see Processing::strokeJoin()
	 * @param type    The stroke join type for this drawable.
	 * @return This drawable.
	 */
	public HDrawable strokeJoin(int type) {
		_strokeJoin = type;
		onStyleChange();
		return this;
	}
	/**
	 * Returns the stroke join for this drawable.
	 * 
	 * @return The integer representing the stroke join of this drawable.
	 */
	public int strokeJoin() {
		return _strokeJoin;
	}
	/**
	 * Sets the stroke weight, or thickness of this drawable.
	 * 
	 * @chainable
	 * @param f    The thickness for this drawable's stroke in pixels.
	 * @return This drawable
	 */
	public HDrawable strokeWeight(float f) {
		_strokeWeight = f;
		onStyleChange();
		return this;
	}
	/**
	 * Returns the stroke weight, or thickness of this drawable.
	 * 
	 * @return The stroke weight for this drawable, in pixels.
	 */
	public float strokeWeight() {
		return _strokeWeight;
	}
	public HDrawable stylesChildren(boolean b) {
		_flags = HMath.setBits(_flags, BITMASK_STYLES_CHILDREN, b);
		return this;
	}
	public boolean stylesChildren() {
		return HMath.hasBits(_flags, BITMASK_STYLES_CHILDREN);
	}
	protected void onStyleChange() {
		if(stylesChildren()) {
			HDrawable d = _firstChild;
			while(d!=null) {
				d._stroke = _stroke;
				d._strokeWeight = _strokeWeight;
				d._strokeJoin = _strokeJoin;
				d._strokeCap = _strokeCap;
				d._fill = _fill;
				d = d._next;
			}
		}
	}
	/**
	 * Sets the rotation for this drawable, in degrees.
	 * 
	 * @chainable
	 * @param deg    The new rotation for this drawable, in degrees.
	 * @return This drawable.
	 */
	public HDrawable rotation(float deg) {
		return rotationZ(deg);
	}
	/**
	 * Returns the rotation for this drawable, in degrees.
	 * 
	 * @return This drawable's rotation in degrees.
	 */
	public float rotation() {
		return rotationZ();
	}
	public HDrawable rotationRad(float rad) {
		return rotationZRad(rad);
	}
	public float rotationRad() {
		return rotationZRad();
	}
	/**
	 * Shifts this drawable's current rotation by the passed value, in degrees.
	 * 
	 * @chainable
	 * @param deg    The amount of degrees for the current rotation to be shifted.
	 * @return This drawable.
	 */
	public HDrawable rotate(float deg) {
		return rotateZ(deg);
	}
	/**
	 * Shifts this drawable's current rotation by the passed value, in radians.
	 * 
	 * @chainable
	 * @param rad    The amount of radians for the current rotation to be shifted.
	 * @return This drawable.
	 */
	public HDrawable rotateRad(float rad) {
		return rotateZRad(rad);
	}
	public HDrawable rotationX(float deg) {
		return rotationXRad(deg * HConstants.D2R);
	}
	public float rotationX() {
		return rotationXRad() * HConstants.R2D;
	}
	public HDrawable rotationXRad(float rad) {
		if(rotatesChildren()) {
			for(HDrawable d=_firstChild;d!=null;) d=d.rotationXRad(rad).next();
		} else _rotationXRad = rad;
		return this;
	}
	public float rotationXRad() {
		return (rotatesChildren() && _firstChild!=null)?
			_firstChild.rotationXRad() : _rotationXRad;
	}
	public HDrawable rotateX(float deg) {
		return rotationXRad(_rotationXRad + deg*HConstants.D2R);
	}
	public HDrawable rotateXRad(float rad) {
		return rotationXRad(_rotationXRad + rad);
	}
	public HDrawable rotationY(float deg) {
		return rotationYRad(deg * HConstants.D2R);
	}
	public float rotationY() {
		return rotationYRad() * HConstants.R2D;
	}
	public HDrawable rotationYRad(float rad) {
		if(rotatesChildren()) {
			for(HDrawable d=_firstChild;d!=null;) d=d.rotationYRad(rad).next();
		} else _rotationYRad = rad;
		return this;
	}
	public float rotationYRad() {
		return (rotatesChildren() && _firstChild!=null)?
				_firstChild.rotationYRad() : _rotationYRad;
	}
	public HDrawable rotateY(float deg) {
		return rotationYRad(_rotationYRad + deg*HConstants.D2R);
	}
	public HDrawable rotateYRad(float rad) {
		return rotationYRad(_rotationYRad + rad);
	}
	public HDrawable rotationZ(float deg) {
		return rotationZRad(deg * HConstants.D2R);
	}
	public float rotationZ() {
		return rotationZRad() * HConstants.R2D;
	}
	public HDrawable rotationZRad(float rad) {
		if(rotatesChildren()) {
			for(HDrawable d=_firstChild;d!=null;) d=d.rotationZRad(rad).next();
		} else _rotationZRad = rad;
		return this;
	}
	public float rotationZRad() {
		return (rotatesChildren() && _firstChild!=null)?
				_firstChild.rotationZRad() : _rotationZRad;
	}
	public HDrawable rotateZ(float deg) {
		return rotationZRad(_rotationZRad + deg*HConstants.D2R);
	}
	public HDrawable rotateZRad(float rad) {
		return rotationZRad(_rotationZRad + rad);
	}
	public HDrawable rotatesChildren(boolean b) {
		_flags = HMath.setBits(_flags, BITMASK_ROTATES_CHILDREN, b);
		return this;
	}
	public boolean rotatesChildren() {
		return HMath.hasBits(_flags, BITMASK_ROTATES_CHILDREN);
	}
	/**
	 * Sets the transparency for this drawable.
	 * 
	 * The argument for this method can be any number from 0 to 255. Otherwise,
	 * if the argument is less than 0, the alpha will be 0; and if the argument
	 * is greater than 255, the alpha will be 255.
	 * 
	 * Note that transparency _is independent from fill and stroke color_, even
	 * if the fill and stroke can also have alpha values. And unlike fill and
	 * stroke, this transparency can be passed down to this drawable's children.
	 * 
	 * Also note that HDrawable actually stores `alpha` as a percentage ranging
	 * from 0 to 1 to keep the draw() and paintAll() methods a bit faster.
	 * 
	 * @chainable
	 * @see alphaPc(float)
	 * @param a    The new alpha for this drawable.
	 * @return This drawable.
	 */
	public HDrawable alpha(int a) {
		return alphaPc(a/255f);
	}
	/**
	 * Returns the transparency for this drawable, ranging at `[0,255]`.
	 * 
	 * You can be assured that the return value will only be in the
	 * aforementioned range.
	 * 
	 * @see alphaPc()
	 * @return The alpha for this drawable with range `(0,255)`.
	 */
	public int alpha() {
		return Math.round( alphaPc()*255 );
	}
	/**
	 * Sets the transparency for this drawable, as percentage.
	 * 
	 * The argument for this method can be any number from 0 to 1. Otherwise, if
	 * the argument is less than 0f, the alpha will be 0f; and if the argument
	 * is greater than 1f, the alpha will be 1f. 
	 * 
	 * @chainable
	 * @see alphaPc(float)
	 * @param f    The new alpha for this drawable, as percentage.
	 * @return This drawable.
	 */
	public HDrawable alphaPc(float f) {
		_alphaPc = (f<0)? 0 : (f>1)? 1 : f;
		return this;
	}
	/**
	 * Returns the transparency for this drawable, ranging from 0 to 255.
	 * 
	 * You can be assured that the return value will only be in the
	 * aforementioned range.
	 * 
	 * @see alpha()
	 * @return The alpha for this drawable with range `(0f,1f)`.
	 */
	public float alphaPc() {
		return (_alphaPc<0)? 0 : _alphaPc;
	}
	/**
	 * Sets the visibility of this drawable.
	 * 
	 * Technically this method manipulates the alpha of this drawable. If `v` is
	 * false, the alpha will become the negative of the current alpha, making
	 * this drawable invisible. Otherwise, if `v` is true, the alpha will become
	 * the positive of the current alpha (or 1f if the current alpha is 0),
	 * making this drawable visible. 
	 * 
	 * @chainable
	 * @see show(), hide()
	 * @param v    True if this drawable will be visible, false if invisible.
	 * @return This drawable.
	 */
	public HDrawable visibility(boolean v) {
		if( v && (_alphaPc==0) ) {
			_alphaPc = 1;
		} else if( v == (_alphaPc<0) ) {
			_alphaPc = -_alphaPc;
		}
		return this;
	}
	/**
	 * Returns the visibility of this drawable.
	 * 
	 * @return True if this drawable is visible, otherwise false.
	 */
	public boolean visibility() {
		return _alphaPc > 0;
	}
	/**
	 * Makes this drawable visible by calling `visibility(true)`.
	 * 
	 * @chainable
	 * @see visibility(boolean), hide()
	 * @return This drawable.
	 */
	public HDrawable show() {
		return visibility(true);
	}
	/**
	 * Makes this drawable invisible by calling `visibility(false)`.
	 * 
	 * @chainable
	 * @see visibility(boolean), show()
	 * @return This drawable.
	 */
	public HDrawable hide() {
		return visibility(false);
	}
	/**
	 * Shifts the current alpha of this drawable by the given value.
	 * 
	 * @chainable
	 * @see alpha(int)
	 * @param da    The amount that the alpha will be shifted.
	 * @return This drawable.
	 */
	public HDrawable alphaShift(int da) {
		return alphaShiftPc( da/255f );
	}
	/**
	 * Shifts the current alpha of this drawable, by percentage.
	 * 
	 * @chainable.
	 * @see alphaPc(float)
	 * @param f    The amount that alpha will be shifted, by percentage.
	 * @return This drawable.
	 */
	public HDrawable alphaShiftPc(float f) {
		return alphaPc(_alphaPc + f);
	}
	public float x2u(float px) {
		return px / (_width==0? 100 : _width);
	}
	public float y2v(float px) {
		return px / (_height==0? 100 : _height);
	}
	public float u2x(float pc) {
		return pc * _width;
	}
	public float v2y(float pc) {
		return pc * _height;
	}
	/**
	 * Assigns an _extras bundle_ for this drawable to hold arbitrary data.
	 * 
	 * This follows the same idea as with Android's Bundles.
	 * 
	 * @chainable
	 * @see Android::Bundle
	 * @param b    The new extras bundle for this drawable.
	 * @return This drawable.
	 */
	public HDrawable extras(HBundle b) {
		_extras = b;
		return this;
	}
	/**
	 * Returns this drawable's _extras bundle_.
	 * 
	 * @return The extras bundle for this drawable.
	 */
	public HBundle extras() {
		return _extras;
	}
	/**
	 * Puts any arbitrary object into this drawable's _extras bundle_.
	 * 
	 * If the extras bundle is null, this method will create a new one.
	 * 
	 * @chainable
	 * @see HBundle.obj(String,Object)
	 * @param key    The string that will be the key for `value`.
	 * @param value  The object to be stored.
	 * @return This drawable.
	 */
	public HDrawable obj(String key, Object value) {
		if(_extras == null) _extras = new HBundle();
		_extras.obj(key,value);
		return this;
	}
	/**
	 * Puts any arbitrary native value into this drawable's _extras bundle_.
	 * 
	 * If the extras bundle is null, this method will create a new one.
	 * 
	 * @chainable
	 * @see HBundle.num(String,float)
	 * @param key    The string that will be the key for `value`.
	 * @param value  The native value to be stored.
	 * @return This drawable.
	 */
	public HDrawable num(String key, float value) {
		if(_extras == null) _extras = new HBundle();
		_extras.num(key,value);
		return this;
	}
	/**
	 * Puts any arbitrary boolean value into this drawable's _extras bundle_.
	 * 
	 * @chainable
	 * @see HBundle.bool(String,boolean)
	 * @param key    The string the will be the key for `value`.
	 * @param value  The boolean to be stored
	 * @return This drawable.
	 */
	public HDrawable bool(String key, boolean value) {
		if(_extras == null) _extras = new HBundle();
		_extras.bool(key,value);
		return this;
	}
	/**
	 * Returns the object from the _extras bundle_ with the corresponding key.
	 * 
	 * If the extras bundle is null, this method will return null.
	 * 
	 * @see HBundle.num(String)
	 * @param key   The key that will be used to fetch the object.
	 * @return The object with the corresponding key.
	 */
	public Object obj(String key) {
		return (_extras==null)? null : _extras.obj(key);
	}
	/**
	 * Returns the string from the _extras bundle_ with the corresponding key.
	 * 
	 * This is a shorthand for `(String) obj(key)`. If the extras bundle is
	 * null, this method will return null.
	 * 
	 * @see HBundle.str(String)
	 * @param key    The key that will be used to fetch the string.
	 * @return The string with the corresponding key.
	 */
	public String str(String key) {
		return (_extras==null)? null : _extras.str(key);
	}
	/**
	 * Returns the number from the _extras bundle_ with the corresponding key.
	 * 
	 * If the extras bundle is null, this method will return 0.
	 * 
	 * @see HBundle.num(String)
	 * @param key    The key that will be used to fetch the float value.
	 * @return The float value with the corresponding key.
	 */
	public float num(String key) {
		return (_extras==null)? 0 : _extras.num(key);
	}
	/**
	 * Returns the rounded number from the _extras bundle_ with the corresponding key.
	 * 
	 * If the extras bundle is null, this method will return 0.
	 * 
	 * @param key    The key that will be used to fetch the integer value.
	 * @return The integer value with the corresponding key.
	 */
	public int numI(String key) {
		return (_extras==null)? 0 : _extras.numI(key);
	}
	/**
	 * Returns the equivalent boolean value from the _extras bundle_ with the corresponding key.
	 * 
	 * @see HBundle.bool(String)
	 * @param key
	 * @return
	 */
	public boolean bool(String key) {
		return (_extras==null)? false : _extras.bool(key);
	}
	public boolean contains(float absX, float absY, float absZ) {
		PApplet app = H.app();
		absZ -= _z;
		return contains(
			app.screenX(absX,absY,absZ),
			app.screenY(absX,absY,absZ));
	}
	public boolean contains(float absX, float absY) {
		float[] rel = HMath.relLocArr(this, absX, absY);
		rel[0] += anchorX();
		rel[1] += anchorY();
		return containsRel(rel[0], rel[1]);
	}
	public boolean containsRel(float relX, float relY, float relZ) {
		PApplet app = H.app();
		relZ -= _z;
		return containsRel(
			app.screenX(relX,relY,relZ),
			app.screenY(relX,relY,relZ));
	}
	public boolean containsRel(float relX, float relY) {
		return (0 <= relX) && (relX <= _width) &&
			(0 <= relY) && (relY <= _height);
	}
	/**
	 * Applies this drawable's styling to the given graphics context.
	 * 
	 * This method is primarily used by some subclasses' `draw()` method.
	 * 
	 * @param g    The graphics context for this drawable.
	 * @param currAlphaPc    The current alpha value in the draw cycle.
	 */
	protected void applyStyle(PGraphics g, float currAlphaPc) {
		float faPc = currAlphaPc * (_fill >>> 24);
		g.fill(_fill | 0xFF000000, Math.round(faPc));
		if(_strokeWeight > 0) {
			float saPc = currAlphaPc * (_stroke >>> 24);
			g.stroke(_stroke | 0xFF000000, Math.round(saPc));
			g.strokeWeight(_strokeWeight);
			g.strokeCap(_strokeCap);
			g.strokeJoin(_strokeJoin);
		} else g.noStroke();
	}
	/**
	 * Prepares the environment for drawing this drawable and its children.
	 * 
	 * This method is primarily called internally during the draw cycle and
	 * probably wouldn't need to be called directly.
	 * 
	 * @param g    The graphics context for this drawable.
	 * @param usesZ    Indicates if z-coordinates are used.
	 * @param currAlphaPc    The current alpha value in the draw cycle.
	 */
	public void paintAll(PGraphics g, boolean usesZ, float currAlphaPc) {
		if(_alphaPc<=0) return;
		g.pushMatrix();
			if(usesZ) {
				g.translate(_x,_y,_z);
				g.rotateX(_rotationXRad);
				g.rotateY(_rotationYRad);
				g.rotateZ(_rotationZRad);
			} else {
				g.translate(_x,_y);
				g.rotate(_rotationZRad);
			}
			currAlphaPc *= _alphaPc;
			draw(g, usesZ,-anchorX(),-anchorY(),currAlphaPc);
			HDrawable child = _firstChild;
			while(child != null) {
				child.paintAll(g, usesZ, currAlphaPc);
				child = child._next;
			}
		g.popMatrix();
	}
	/**
	 * Draws this drawable.
	 * 
	 * This method is abstract and is meant to be implemented by the children
	 * of this class. Also, this method is primarily called internally during
	 * the draw cycle and probably wouldn't need to be called directly.
	 * 
	 * @param g     The graphics context for this drawable.
	 * @param usesZ Indicates if z-coordinates are used.
	 * @param drawX The x coordinate where this drawable would consider as 0
	 * @param drawY The y coordinate where this drawable would consider as 0
	 * @param currAlphaPc    The current alpha value in the draw cycle.
	 */
	public abstract void draw( PGraphics g, boolean usesZ,
		float drawX, float drawY, float currAlphaPc);
	/**
	 * An HIterator used for iterating through HDrawable's children.
	 * 
	 * @author james
	 */
	public static class HDrawableIterator implements Iterator<HDrawable> {
		private HDrawable parent, d1, d2;
		public HDrawableIterator(HDrawable parentDrawable) {
			parent = parentDrawable;
			d1 = parent._firstChild;
			if(d1 != null) d2 = d1._next;
		}
		public boolean hasNext() {
			return (d1 != null);
		}
		public HDrawable next() {
			HDrawable nxt = d1;
			d1 = d2;
			if(d2 != null) d2 = d2._next;
			return nxt;
		}
		public void remove() {
			if(d1 != null) d1.popOut();
		}
	}
}
/*
 * HYPE_Processing
 * http:
 * 
 * Copyright (c) 2013 Joshua Davis & James Cruz
 * 
 * Distributed under the BSD License. See LICENSE.txt for details.
 * 
 * All rights reserved.
 */
public static abstract class HDrawable3D extends HDrawable {
	public static final float DEFAULT_DEPTH = 100; 
	protected float _depth;
	protected float _anchorW;
	public HDrawable3D() {
		_depth = DEFAULT_DEPTH;
	}
	public HDrawable3D size(float s) {
		return size(s,s,s);
	}
	public HDrawable3D size(PVector s) {
		return size(s.x,s.y,s.z);
	}
	public HDrawable3D size(float w, float h, float d) {
		_width = w;
		_height = h;
		_depth = d;
		return this;
	}
	public PVector size() {
		return new PVector(_width,_height,_depth);
	}
	public HDrawable3D depth(float f) {
		_depth = f;
		return this;
	}
	public float depth() {
		return _depth;
	}
	public HDrawable3D scale(float s) {
		return scale(s,s,s);
	}
	public HDrawable3D scale(float sw, float sh, float sd) {
		return (HDrawable3D) depth(sd).scale(sw,sh);
	}
	public HDrawable3D anchor(float ancx, float ancy, float ancz) {
		return (HDrawable3D) anchorZ(ancz).anchorX(ancx).anchorY(ancy);
	}
	public HDrawable3D anchorAt(int where) {
		if( (where & HConstants.CENTER_X) != 0 ) {
			anchorU(0.5f);
		} else if( (where & HConstants.LEFT) != 0 ) {
			anchorU(0);
		} else if( (where & HConstants.RIGHT) !=  0 ) {
			anchorU(1);
		}
		if( (where & HConstants.CENTER_Y) != 0 ) {
			anchorV(0.5f);
		} else if( (where & HConstants.TOP) != 0 ) {
			anchorV(0);
		} else if( (where & HConstants.BOTTOM) !=  0 ) {
			anchorV(1);
		}
		if( (where & HConstants.CENTER_Z) != 0 ) {
			anchorW(0.5f);
		} else if( (where & HConstants.BACK) != 0 ) {
			anchorW(0);
		} else if( (where & HConstants.FRONT) !=  0 ) {
			anchorW(1);
		}
		return this;
	}
	protected void onResize(float oldW, float oldH, float newW, float newH) {
		super.onResize(oldW, oldH, newW, newH);
	}
	public PVector anchor() {
		return new PVector(anchorX(), anchorY(), anchorZ());
	}
	public PVector anchorUV() {
		return new PVector(_anchorU, _anchorV, _anchorW);
	}
	public HDrawable3D anchorZ(float f) {
		return anchorZ(z2w(f));
	}
	public float anchorZ() {
		return w2z(_anchorW);
	}
	public HDrawable3D anchorW(float f) {
		_anchorW = f;
		return this;
	}
	public float anchorW() {
		return _anchorW;
	}
	public float z2w(float px) {
		return px / (_depth==0? DEFAULT_DEPTH : _depth);
	}
	public float w2z(float pc) {
		return pc * _depth;
	}
}
/*
 * HYPE_Processing
 * http:
 * 
 * Copyright (c) 2013 Joshua Davis & James Cruz
 * 
 * Distributed under the BSD License. See LICENSE.txt for details.
 * 
 * All rights reserved.
 */
public static class HDrawablePool implements Iterable<HDrawable> {
	private HLinkedHashSet<HDrawable> _activeSet, _inactiveSet;
	private ArrayList<HDrawable> _prototypes;
	private HCallback _onCreate, _onRequest, _onRelease;
	private HLayout _layout;
	private HColorist _colorist;
	private HDrawable _autoParent;
	private int _max;
	public HDrawablePool() {
		this(64);
	}
	public HDrawablePool(int maximumDrawables) {
		_max = maximumDrawables;
		_activeSet = new HLinkedHashSet<HDrawable>();
		_inactiveSet = new HLinkedHashSet<HDrawable>();
		_prototypes = new ArrayList<HDrawable>();
		_onCreate = _onRequest = _onRelease = HConstants.NOP;
	}
	public int max() {
		return _max;
	}
	public HDrawablePool max(int m) {
		_max = m;
		return this;
	}
	public int numActive() {
		return _activeSet.size();
	}
	public int numInactive() {
		return _inactiveSet.size();
	}
	public int currentIndex() {
		return _activeSet.size() - 1;
	}
	public HLayout layout() {
		return _layout;
	}
	public HDrawablePool layout(HLayout newLayout) {
		_layout = newLayout;
		return this;
	}
	public HColorist colorist() {
		return _colorist;
	}
	public HDrawablePool colorist(HColorist newColorist) {
		_colorist = newColorist;
		return this;
	}
	public HDrawablePool onCreate(HCallback callback) {
		_onCreate = (callback==null)? HConstants.NOP : callback;
		return this;
	}
	public HCallback onCreate() {
		return _onCreate;
	}
	public HDrawablePool onRequest(HCallback callback) {
		_onRequest = (callback==null)? HConstants.NOP : callback;
		return this;
	}
	public HCallback onRequest() {
		return _onRequest;
	}
	public HDrawablePool onRelease(HCallback callback) {
		_onRelease = (callback==null)? HConstants.NOP : callback;
		return this;
	}
	public HCallback onRelease() {
		return _onRelease;
	}
	public HDrawablePool autoParent(HDrawable parent) {
		_autoParent = parent;
		return this;
	}
	public HDrawablePool autoAddToStage() {
		_autoParent = H.stage();
		return this;
	}
	public HDrawable autoParent() {
		return _autoParent;
	}
	public boolean isFull() {
		return count() >= _max;
	}
	public int count() {
		return _activeSet.size() + _inactiveSet.size();
	}
	public HDrawablePool destroy() {
		_activeSet.removeAll();
		_inactiveSet.removeAll();
		_prototypes.clear();
		_onCreate = _onRequest = _onRelease = HConstants.NOP;
		_layout = null;
		_autoParent = null;
		_max = 0;
		return this;
	}
	public HDrawablePool drain() {
		return drain(true);
	}
	public HDrawablePool drain(boolean resetLayout) {
		for (HDrawable d : _activeSet) {
			if(_autoParent != null) _autoParent.remove(d);
			_onRelease.run(d);
		}
		_activeSet.removeAll();
		_inactiveSet.removeAll();
		return this;
	}
	public HDrawablePool add(HDrawable prototype, int frequency) {
		if(prototype == null) {
			HWarnings.warn("Null Prototype", "HDrawablePool.add()",
					HWarnings.NULL_ARGUMENT);
		} else {
			_prototypes.add(prototype);
			while(frequency-- > 0) _prototypes.add(prototype);
		}
		return this;
	}
	public HDrawablePool add(HDrawable prototype) {
		return add(prototype,1);
	}
	public HDrawable request() {
		if(_prototypes.size() <= 0) {
			HWarnings.warn("No Prototype", "HDrawablePool.request()",
					HWarnings.NO_PROTOTYPE);
			return null;
		}
		HDrawable drawable;
		boolean onCreateFlag = false;
		if(_inactiveSet.size() > 0) {
			drawable = _inactiveSet.pull();
		} else if(count() < _max) {
			drawable = createRandomDrawable();
			onCreateFlag = true;
		} else return null;
		_activeSet.add(drawable);
		if(_autoParent != null) _autoParent.add(drawable);
		if(_layout != null) _layout.applyTo(drawable);
		if(_colorist != null) _colorist.applyColor(drawable);
		if(onCreateFlag) _onCreate.run(drawable);
		_onRequest.run(drawable);
		return drawable;
	}
	public HDrawablePool requestAll() {
		if(_prototypes.size() <= 0) {
			HWarnings.warn("No Prototype", "HDrawablePool.requestAll()",
					HWarnings.NO_PROTOTYPE);
		} else {
			while(count() < _max) request();
		}
		return this;
	}
	public HDrawablePool shuffleRequestAll() {
		HDrawable tempParent = null;
		if(_autoParent != null) {
			tempParent = _autoParent;
			_autoParent = null;
		}
		if(_prototypes.size() <= 0) {
			HWarnings.warn("No Prototype", "HDrawablePool.shuffleRequestAll()",
					HWarnings.NO_PROTOTYPE);
		} else {
			while(count() < _max) request();
		}
		_activeSet.shuffle();
		if(tempParent != null) {
			_autoParent = tempParent;
			tempParent = null;
			for (HDrawable d : _activeSet) {
				_autoParent.add(d);
			}
		}
		return this;
	}
	public boolean release(HDrawable d) {
		if(_activeSet.remove(d)) {
			_inactiveSet.add(d);
			if(_autoParent != null) _autoParent.remove(d);
			_onRelease.run(d);
			return true;
		}
		return false;
	}
	public HLinkedHashSet<HDrawable> activeSet() {
		return _activeSet;
	}
	public HLinkedHashSet<HDrawable> inactiveSet() {
		return _inactiveSet;
	}
	private HDrawable createRandomDrawable() {
		int index = HMath.randomInt(_prototypes.size());
		return _prototypes.get(index).createCopy();
	}
	public Iterator<HDrawable> iterator() {
		return _activeSet.iterator();
	}
}
/*
 * HYPE_Processing
 * http:
 * 
 * Copyright (c) 2013 Joshua Davis & James Cruz
 * 
 * Distributed under the BSD License. See LICENSE.txt for details.
 * 
 * All rights reserved.
 */
public static class HEllipse extends HDrawable {
	private int _mode;
	private float _startRad, _endRad;
	public HEllipse() {
		_mode = PConstants.PIE;
	}
	public HEllipse(float ellipseRadius) {
		this();
		radius(ellipseRadius);
	}
	public HEllipse(float radiusX, float radiusY) {
		this();
		radius(radiusX,radiusY);
	}
	public HEllipse createCopy() {
		HEllipse copy = new HEllipse();
		copy.copyPropertiesFrom(this);
		return copy;
	}
	public HEllipse radius(float r) {
		size(r*2);
		return this;
	}
	public HEllipse radius(float radiusX, float radiusY) {
		size(radiusX*2,radiusY*2);
		return this;
	}
	public HEllipse radiusX(float radiusX) {
		width(radiusX * 2);
		return this;
	}
	public float radiusX() {
		return _width/2;
	}
	public HEllipse radiusY(float radiusY) {
		height(radiusY * 2);
		return this;
	}
	public float radiusY() {
		return _height/2;
	}
	public boolean isCircle() {
		return _width == _height;
	}
	public HEllipse mode(int t) {
		_mode = t;
		return this;
	}
	public float mode() {
		return _mode;
	}
	public HEllipse start(float deg) {
		return startRad(deg * H.D2R);
	}
	public float start() {
		return _startRad * H.R2D;
	}
	public HEllipse startRad(float rad) {
		_startRad = HMath.normalizeAngleRad(rad);
		if(_startRad > _endRad) _endRad += PConstants.TWO_PI;
		return this;
	}
	public float startRad() {
		return _startRad;
	}
	public HEllipse end(float deg) {
		return endRad(deg * H.D2R);
	}
	public float end() {
		return _endRad * H.R2D;
	}
	public HEllipse endRad(float rad) {
		_endRad = HMath.normalizeAngleRad(rad);
		if(_startRad > _endRad) _endRad += PConstants.TWO_PI;
		return this;
	}
	public float endRad() {
		return _endRad;
	}
	public boolean containsRel(float relX, float relY) {
		float cx = _width/2;
		float cy = _height/2;
		float dcx = relX - cx;
		float dcy = relY - cy;
		boolean inEllipse = ((dcx*dcx)/(cx*cx) + (dcy*dcy)/(cy*cy) <= 1);
		if(_startRad == _endRad) return inEllipse;
		else if(!inEllipse) return false;
		if(_mode == PConstants.PIE) {
			float ptAngle = (float) Math.atan2(dcy*cx, dcx*cy);
			if(_startRad > ptAngle) ptAngle += PConstants.TWO_PI;
			return (_startRad<=ptAngle && ptAngle<=_endRad);
		} else {
			float end = HMath.squishAngleRad(cx, cy, _endRad);
			float start = HMath.squishAngleRad(cx, cy, _startRad);
			float[] pt1 = HMath.ellipsePointRadArr(cx,cy, cx,cy, end);
			float[] pt2 = HMath.ellipsePointRadArr(cx,cy, cx,cy, start);
			return HMath.rightOfLine(pt1[0],pt1[1], pt2[0],pt2[1], relX,relY);
		}
	}
	public void draw( PGraphics g, boolean usesZ,
		float drawX,float drawY,float alphaPc
	) {
		applyStyle(g,alphaPc);
		drawX += _width/2;
		drawY += _height/2;
		if(_startRad == _endRad) {
			g.ellipse(drawX, drawY, _width, _height);
		} else {
			g.arc(drawX,drawY,_width,_height,_startRad,_endRad,_mode);
		}
	}
}
/*
 * HYPE_Processing
 * http:
 * 
 * Copyright (c) 2013 Joshua Davis & James Cruz
 * 
 * Distributed under the BSD License. See LICENSE.txt for details.
 * 
 * All rights reserved.
 */
public static class HFollow extends HBehavior {
	private float _ease, _spring, _dx, _dy;
	private HLocatable _goal;
	private HLocatable _follower;
	public HFollow() {
		this(1);
	}
	public HFollow(float ease) {
		this(ease,0);
	}
	public HFollow(float ease, float spring) {
		this(ease, spring, H.mouse());
	}
	public HFollow(float ease, float spring, HLocatable goal) {
		_ease = ease;
		_spring = spring;
		_goal = goal;
	}
	public HFollow ease(float f) {
		_ease = f;
		return this;
	}
	public float ease() {
		return _ease;
	}
	public HFollow spring(float f) {
		_spring = f;
		return this;
	}
	public float spring() {
		return _spring;
	}
	public HFollow goal(HLocatable g) {
		_goal = g;
		return this;
	}
	public HLocatable goal() {
		return _goal;
	}
	public HFollow followMouse() {
		_goal = H.mouse();
		return this;
	}
	public HFollow target(HLocatable f) {
		if(f == null) unregister();
		else register();
		_follower = f;
		return this;
	}
	public HLocatable target() {
		return _follower;
	}
	public void runBehavior(PApplet app) {
		if(_follower==null || ! H.mouse().started()) return;
		_dx = _dx*_spring + (_goal.x()-_follower.x()) * _ease;
		_dy = _dy*_spring + (_goal.y()-_follower.y()) * _ease;
		_follower.x(_follower.x() + _dx);
		_follower.y(_follower.y() + _dy);
	}
	public HFollow register() {
		return (HFollow) super.register();
	}
	public HFollow unregister() {
		return (HFollow) super.unregister();
	}
}
/*
 * HYPE_Processing
 * http:
 * 
 * Copyright (c) 2013 Joshua Davis & James Cruz
 * 
 * Distributed under the BSD License. See LICENSE.txt for details.
 * 
 * All rights reserved.
 */
public static class HGridLayout implements HLayout {
	private int _currentIndex, _numCols, _numRows;
	private float _startX, _startY, _startZ, _xSpace, _ySpace, _zSpace;
	public HGridLayout() {
		_xSpace = _ySpace = _zSpace = _numCols = 16;
		_numRows = 0;
	}
	public HGridLayout(int numOfColumns) {
		this();
		_numCols = numOfColumns;
	}
	public HGridLayout(int numOfColumns, int numOfRows) {
		this();
		_numCols = numOfColumns;
		_numRows = numOfRows;
	}
	public HGridLayout currentIndex(int i) {
		_currentIndex = i;
		return this;
	}
	public int currentIndex() {
		return _currentIndex;
	}
	public HGridLayout resetIndex() {
		_currentIndex = 0;
		return this;
	}
	public HGridLayout cols(int numOfColumns) {
		_numCols = numOfColumns;
		return this;
	}
	public int cols() {
		return _numCols;
	}
	public HGridLayout rows(int numOfRows) {
		_numRows = numOfRows;
		return this;
	}
	public int rows() {
		return _numRows;
	}
	public PVector startLoc() {
		return new PVector(_startX, _startY, _startZ);
	}
	public HGridLayout startLoc(float x, float y) {
		_startX = x;
		_startY = y;
		_startZ = 0;
		return this;
	}
	public HGridLayout startLoc(float x, float y, float z) {
		_startX = x;
		_startY = y;
		_startZ = z;
		return this;
	}
	public float startX() {
		return _startX;
	}
	public HGridLayout startX(float x) {
		_startX = x;
		return this;
	}
	public float startY() {
		return _startY;
	}
	public HGridLayout startY(float y) {
		_startY = y;
		return this;
	}
	public float startZ() {
		return _startZ;
	}
	public HGridLayout startZ(float z) {
		_startZ = z;
		return this;
	}
	public PVector spacing() {
		return new PVector(_xSpace, _ySpace, _zSpace);
	}
	public HGridLayout spacing(float xSpacing, float ySpacing) {
		_xSpace = xSpacing;
		_ySpace = ySpacing;
		return this;
	}
	public HGridLayout spacing(float xSpacing, float ySpacing, float zSpacing) {
		_xSpace = xSpacing;
		_ySpace = ySpacing;
		_zSpace = zSpacing;
		return this;
	}
	public float spacingX() {
		return _xSpace;
	}
	public HGridLayout spacingX(float xSpacing) {
		_xSpace = xSpacing;
		return this;
	}
	public float spacingY() {
		return _ySpace;
	}
	public HGridLayout spacingY(float ySpacing) {
		_ySpace = ySpacing;
		return this;
	}
	public float spacingZ() {
		return _zSpace;
	}
	public HGridLayout spacingZ(float zSpacing) {
		_zSpace = zSpacing;
		return this;
	}
	public PVector getNextPoint() {
		int layer = 0;
		int row = 0;
		int col = _currentIndex % _numCols;
		if (_numRows > 0) {
			layer = (int) Math.floor( _currentIndex / (_numCols * _numRows) );
			row = (int) Math.floor(_currentIndex / _numCols) - (layer * _numRows);
		} else {
			row = (int) Math.floor(_currentIndex / _numCols);
		}
		++_currentIndex;
		if (_numRows > 0) {
			return new PVector(col*_xSpace + _startX, row*_ySpace + _startY, layer*_zSpace + _startZ);
		} else {
			return new PVector(col*_xSpace + _startX, row*_ySpace + _startY);
		}
	}
	public void applyTo(HDrawable target) {
		target.loc(getNextPoint());
	}
}
/*
 * HYPE_Processing
 * http:
 * 
 * Copyright (c) 2013 Joshua Davis & James Cruz
 * 
 * Distributed under the BSD License. See LICENSE.txt for details.
 * 
 * All rights reserved.
 */
public static class HGroup extends HDrawable {
	public HGroup() {
		transformsChildren(true).stylesChildren(true);
	}
	public HGroup createCopy() {
		HGroup copy = new HGroup();
		copy.copyPropertiesFrom(this);
		return copy;
	}
	public void paintAll(PGraphics g, boolean usesZ, float alphaPc) {
		if(_alphaPc<=0) return;
		g.pushMatrix();
			if(usesZ) g.translate(_x,_y,_z);
			else g.translate(_x,_y);
			g.rotate(_rotationZRad);
			alphaPc *= _alphaPc;
			HDrawable child = _firstChild;
			while(child != null) {
				child.paintAll(g, usesZ, alphaPc);
				child = child.next();
			}
		g.popMatrix();
	}
	public void draw(PGraphics g,boolean b,float x,float y,float f) {}
}
/*
 * HYPE_Processing
 * http:
 * 
 * Copyright (c) 2013 Joshua Davis & James Cruz
 * 
 * HHexLayout
 * by Russell Hay / cordandruss.com / github.com/RussTheAerialist
 *
 * Creates a grid layout that is hex based spiraling out from the center of the sketch
 *
 * References:
 *    www.redblobgames.com/grids/hexagons/
 *    gamedev.stackexchange.com/questions/51264/get-ring-of-tiles-in-hexagon-grid
 * 
 * Distributed under the BSD License. See LICENSE.txt for details.
 * 
 * All rights reserved.
 */
public static class HHexLayout implements HLayout {
	protected int _currentDistanceFromCenter, _currentIndex, _direction;
	protected float _spacing, _offsetX, _offsetY, _adjustX, _adjustY;
	protected PVector _lastPoint;
	public HHexLayout() {
		_spacing = 16;
		_currentIndex = 0;
		_direction = 0;
		_currentDistanceFromCenter = 0;
		_offsetX = H.app().width / 2.0f;
		_offsetY = H.app().height / 2.0f;
		_adjustX = _spacing * 1.25f;
		_adjustY = _spacing * 0.25f;
		_lastPoint = null;
	}
	public HHexLayout spacing(float size) {
		_spacing = size;
		return this;
	}
	public float spacing() {
		return _spacing;
	}
	public HHexLayout offsetX(float value) {
		_adjustX = value;
		return this;
	}
	public HHexLayout offsetY(float value) {
		_adjustY = value;
		return this;
	}
	public float offsetX() { return _adjustX; }
	public float offsetY() { return _adjustY; }
	protected PVector north(PVector in) {
		return north(in, 1);
	}
	protected PVector north(PVector in, int distance) {
		return new PVector(in.x, in.y-distance);
	}
	protected PVector south(PVector in) {
		return new PVector(in.x, in.y+1);
	}
	protected PVector northeast(PVector in) {
		return new PVector(in.x+1, in.y-1);
	}
	protected PVector northwest(PVector in) {
		return new PVector(in.x-1, in.y);
	}
	protected PVector southeast(PVector in) {
		return new PVector(in.x+1, in.y);
	}
	protected PVector southwest(PVector in) {
		return new PVector(in.x-1, in.y+1);
	}
	protected void updateLastPoint() {
		if (_currentIndex > _currentDistanceFromCenter-1) {
			_currentIndex = 0;
			_direction++;
		}
		if (_direction > 5) {
			_direction = 0;
			_currentDistanceFromCenter++;
			_currentIndex = 0;
			_lastPoint = north(new PVector(0,0), _currentDistanceFromCenter);
		}
		if (_lastPoint != null) {
			switch(_direction) {
				case 0:
					_lastPoint = southeast(_lastPoint);
					break;
				case 1:
					_lastPoint = south(_lastPoint);
					break;
				case 2:
					_lastPoint = southwest(_lastPoint);
					break;
				case 3:
					_lastPoint = northwest(_lastPoint);
					break;
				case 4:
					_lastPoint = north(_lastPoint);
					break;
				case 5:
					_lastPoint = northeast(_lastPoint);
					break;
			}
		} else {
			_lastPoint = new PVector(0,0);
			_currentIndex = _currentDistanceFromCenter+1;
			_direction = 7;
		}
	}
	public PVector getNextPoint() {
		++_currentIndex;
		updateLastPoint();
		float x, y;
		x = y = 0;
		x = _spacing * 3.0f/2.0f * _lastPoint.x;
		y = _spacing * H.app().sqrt(3.0f) * (_lastPoint.y + _lastPoint.x/2.0f);
		return new PVector(
			x + _offsetX - _adjustX ,
			y + _offsetY - _adjustY
		);
	}
	public void applyTo(HDrawable target) {
		target.loc(getNextPoint());
	}
}
/*
 * HYPE_Processing
 * http:
 * 
 * Copyright (c) 2013 Joshua Davis & James Cruz
 * 
 * Distributed under the BSD License. See LICENSE.txt for details.
 * 
 * All rights reserved.
 */
public static interface HHittable {
	public boolean contains(float absX, float absY, float absZ);
	public boolean contains(float absX, float absY);
	public boolean containsRel(float relX, float relY, float relZ);
	public boolean containsRel(float relX, float relY);
}
/*
 * HYPE_Processing
 * http:
 * 
 * Copyright (c) 2013 Joshua Davis & James Cruz
 * 
 * Distributed under the BSD License. See LICENSE.txt for details.
 * 
 * All rights reserved.
 */
public static class HImage extends HDrawable implements HImageHolder {
	private PImage _image;
	public HImage() {
		this(null);
	}
	public HImage(Object imgArg) {
		image(imgArg);
	}
	public HImage createCopy() {
		HImage copy = new HImage(_image);
		copy.copyPropertiesFrom(this);
		return copy;
	}
	public HImage resetSize() {
		if(_image == null) size(0f,0f);
		else size(_image.width, _image.height);
		return this;
	}
	public HImage image(Object imgArg) {
		_image = H.getImage(imgArg);
		return resetSize();
	}
	public PImage image() {
		return _image;
	}
	public HImage tint(int clr) {
		fill(clr);
		return this;
	}
	public HImage tint(int clr, int alpha) {
		fill(clr, alpha);
		return this;
	}
	public HImage tint(int r, int g, int b) {
		fill(r,g,b);
		return this;
	}
	public HImage tint(int r, int g, int b, int a) {
		fill(r,g,b,a);
		return this;
	}
	public int tint() {
		return fill();
	}
	public boolean containsRel(float relX, float relY) {
		if(_image == null ||
				_image.width <= 0 || _image.height <= 0 ||
				_width <= 0 || _height <= 0)
			return false;
		int ix = Math.round(relX * _image.width/_width);
		int iy = Math.round(relY * _image.height/_height);
		return (0 < _image.get(ix,iy)>>>24);
	}
	public void draw( PGraphics g, boolean usesZ,
		float drawX, float drawY, float alphaPc
	) {
		if(_image==null) return;
		alphaPc *= (_fill>>>24);
		g.tint( _fill | 0xFF000000, Math.round(alphaPc) );
		int wscale = 1;
		int hscale = 1;
		float w = _width;
		float h = _height;
		if(_width < 0) {
			w = -_width;
			wscale = -1;
			drawX = -drawX;
		}
		if(_height < 0) {
			h = -_height;
			hscale = -1;
			drawY = -drawY;
		}
		g.pushMatrix();
			g.scale(wscale, hscale);
			g.image(_image, drawX,drawY, w,h);
		g.popMatrix();
	}
}
public static interface HImageHolder {
	public HImageHolder image(Object imgArg);
	public PImage image();
}
/*
 * HYPE_Processing
 * http:
 * 
 * Copyright (c) 2013 Joshua Davis & James Cruz
 * 
 * Distributed under the BSD License. See LICENSE.txt for details.
 * 
 * All rights reserved.
 */
public static interface HLayout {
	public void applyTo(HDrawable target);
	public abstract PVector getNextPoint();
}
/*
 * HYPE_Processing
 * http:
 * 
 * Copyright (c) 2013 Joshua Davis & James Cruz
 * 
 * Distributed under the BSD License. See LICENSE.txt for details.
 * 
 * All rights reserved.
 */
public static class HLinkedHashSet<T> extends HLinkedList<T> {
	private HashMap<T,HLinkedListNode<T>> nodeMap;
	public HLinkedHashSet() {
		nodeMap = new HashMap<T, HLinkedListNode<T>>();
	}
	public boolean remove(T content) {
		HLinkedListNode<T> node = nodeMap.get(content);
		if(node==null) return false;
		unregister(content);
		node.popOut();
		--_size;
		return true;
	}
	public boolean add(T content) {
		return contains(content)? false : super.add(content);
	}
	public boolean push(T content) {
		return contains(content)? false : super.push(content);
	}
	public boolean insert(T content, int index) {
		return contains(content)? false : super.insert(content, index);
	}
	public T pull() {
		return unregister(super.pull());
	}
	public T pop() {
		return unregister(super.pop());
	}
	public T removeAt(int index) {
		return unregister(super.removeAt(index));
	}
	public void removeAll() {
		while(_size > 0) pop();
	}
	public boolean contains(T obj) {
		return nodeMap.get(obj) != null;
	}
	protected HLinkedListNode<T> register(T obj) {
		HLinkedListNode<T> node = new HLinkedListNode<T>(obj);
		nodeMap.put(obj,node);
		return node;
	}
	protected T unregister(T obj) {
		nodeMap.remove(obj);
		return obj;
	}
}
/*
 * HYPE_Processing
 * http:
 * 
 * Copyright (c) 2013 Joshua Davis & James Cruz
 * 
 * Distributed under the BSD License. See LICENSE.txt for details.
 * 
 * All rights reserved.
 */
public static class HLinkedList<T> implements Iterable<T> {
	protected HLinkedListNode<T> _firstSentinel, _lastSentinel;
	protected int _size;
	public HLinkedList() {
		_firstSentinel = new HLinkedListNode<T>(null);
		_lastSentinel = new HLinkedListNode<T>(null);
		_lastSentinel.putAfter(_firstSentinel);
	}
	public T first() {
		return _firstSentinel._next._content;
	}
	public T last() {
		return _lastSentinel._prev._content;
	}
	public T get(int index) {
		HLinkedListNode<T> n = nodeAt(index);
		return (n==null)? null : n._content;
	}
	public boolean push(T content) {
		if(content==null) return false;
		register(content).putAfter(_firstSentinel);
		++_size;
		return true;
	}
	public boolean add(T content) {
		if(content==null) return false;
		register(content).putBefore(_lastSentinel);
		++_size;
		return true;
	}
	public boolean insert(T content, int index) {
		if(content==null) return false;
		HLinkedListNode<T> n = (index==_size)? _lastSentinel : nodeAt(index);
		if(n==null) return false;
		register(content).putBefore(n);
		++_size;
		return true;
	}
	public T pop() {
		HLinkedListNode<T> firstNode = _firstSentinel._next;
		if(firstNode._content != null) {
			firstNode.popOut();
			--_size;
		}
		return firstNode._content;
	}
	public T pull() {
		HLinkedListNode<T> lastNode = _lastSentinel._prev;
		if(lastNode._content != null) {
			lastNode.popOut();
			--_size;
		}
		return lastNode._content;
	}
	public T removeAt(int index) {
		HLinkedListNode<T> n = nodeAt(index);
		if(n==null) return null;
		n.popOut();
		--_size;
		return n._content;
	}
	public void removeAll() {
		_lastSentinel.putAfter(_firstSentinel);
		_size = 0;
	}
	/*
		Shuffle list items
		approximate replication of Java's shuffle method
	*/
	public void shuffle() {
		Random rnd = new Random();
		for (int i=_size; i>1; i--) {
			HLinkedListNode<T> i_node = nodeAt(i-1);
			HLinkedListNode<T> rnd_node = nodeAt(rnd.nextInt(i));
			i_node.putAfter(rnd_node);
			i_node = nodeAt(i-1);
			rnd_node.putAfter(i_node);
		}
	}
	public int size() {
		return _size;
	}
	public boolean inRange(int index) {
		return (0 <= index) && (index < _size);
	}
	public HLinkedListIterator<T> iterator() {
		return new HLinkedListIterator<T>(this);
	}
	protected HLinkedListNode<T> nodeAt(int i) {
		int ri; 
		if(i<0) {
			ri = -i;
			i += _size;
		} else {
			ri = _size-i;
		}
		if(!inRange(i)) {
			HWarnings.warn("Out of Range: "+i, "HLinkedList.nodeAt()", null);
			return null;
		}
		HLinkedListNode<T> node;
		if(ri < i) { 
			node = _lastSentinel._prev;
			while(--ri > 0) node = node._prev;
		} else { 
			node = _firstSentinel._next;
			while(i-- > 0) node = node._next;
		}
		return node;
	}
	protected HLinkedListNode<T> register(T obj) {
		return new HLinkedListNode<T>(obj);
	}
	public static class HLinkedListNode<U> extends HNode<HLinkedListNode<U>> {
		private U _content;
		public HLinkedListNode(U nodeContent) {
			_content = nodeContent;
		}
		public U content() {
			return _content;
		}
	}
	public static class HLinkedListIterator<U> implements Iterator<U> {
		private HLinkedList<U> list;
		private HLinkedListNode<U> n1, n2;
		public HLinkedListIterator(HLinkedList<U> parent) {
			list = parent;
			n1 = list._firstSentinel._next;
			if(n1 != null) n2 = n1._next;
		}
		public boolean hasNext() {
			return (n1._content != null);
		}
		public U next() {
			U content = n1._content;
			n1 = n2;
			if(n2 != null) n2 = n2._next; 
			return content;
		}
		public void remove() {
			if(n1._content != null) {
				n1.popOut();
				--list._size;
			}
		}
	}
}
/*
 * HYPE_Processing
 * http:
 * 
 * Copyright (c) 2013 Joshua Davis & James Cruz
 * 
 * Distributed under the BSD License. See LICENSE.txt for details.
 * 
 * All rights reserved.
 */
public static interface HLocatable {
	/**
	 * TODO
	 * 
	 * @return
	 */
	public float x();
	/**
	 * TODO
	 * 
	 * @chainable
	 * @param f
	 * @return
	 */
	public HLocatable x(float f);
	/**
	 * TODO
	 * 
	 * @return
	 */
	public float y();
	/**
	 * TODO
	 * 
	 * @chainable
	 * @param f
	 * @return
	 */
	public HLocatable y(float f);
	/**
	 * TODO
	 * 
	 * @return
	 */
	public float z();
	/**
	 * TODO
	 *
	 * @chainable
	 * @param f
	 * @return
	 */
	public HLocatable z(float f);
}
/*
 * HYPE_Processing
 * 
 * Copyright (c) 2013 Joshua Davis & James Cruz
 *
 * HMagneticField
 * by Benjamin Fox / twitter.com/tracerstar
 * https://github.com/tracerstar
 * 
 * Distributed under the BSD License. See LICENSE.txt for details.
 * 
 * All rights reserved.
 */
public static class HMagneticField extends HBehavior {
	private ArrayList<HPole> _poles;
	private HLinkedHashSet<HDrawable> _targets;
	public HMagneticField() {
		_poles = new ArrayList<HMagneticField.HPole>();
		_targets = new HLinkedHashSet<HDrawable>();
	}
	public HMagneticField addMagnet(float nx, float ny, float sx, float sy) {
		addPole(nx, ny, 1);
		addPole(sx, sy, -1);
		return this;
	}
	public HMagneticField addPole(float x, float y, float polarity) {
		HPole p = new HPole(x, y, polarity);
		_poles.add(p);
		return this;	
	}
	public HPole pole(int index) {
		return _poles.get(index);
	}
	public HMagneticField removePole(int index) {
		_poles.remove(index);
		return this;
	}
	public HMagneticField addTarget(HDrawable d) {
		if(_targets.size() <= 0) register();
		_targets.add(d);
		return this;
	}
	public HMagneticField removeTarget(HDrawable d) {
		_targets.remove(d);
		if(_targets.size() <= 0) unregister();
		return this;
	}
	public float getRotation(float x, float y) {
		int poleCount = _poles.size();
		PVector v1 = new PVector(0, 0);
		PVector v2 = new PVector(x, y); 
		PVector distance = new PVector(0, 0);
		PVector force = new PVector(0, 0);
		float d = 0;
		for(int i=0; i<poleCount; i++) {
			HPole p = _poles.get(i);
			v1.x = p._x;
			v1.y = p._y;
			if (p._polarity < 0) {
				distance = PVector.sub(v1, v2);
			} else {
				distance = PVector.sub(v2, v1);	
			}
			d = distance.mag() / 5;
			distance.normalize();
			distance.mult(abs(p._polarity));
			distance.div(d);
			force.add(distance);
		}
    	return atan2(force.y, force.x);
	}
	public void runBehavior(PApplet app) {
		for(HDrawable d : _targets) d.rotationRad( getRotation(d.x(), d.y()) );
	}
	public HMagneticField register() {
		return (HMagneticField) super.register();
	}
	public HMagneticField unregister() {
		return (HMagneticField) super.unregister();
	}
	public static class HPole {
		public float _x, _y, _polarity;
		public HPole(float x, float y, float polarity) {
			_x = x;
			_y = y;
			_polarity = polarity;
		}
	}
}
//UTF-8
/*
 * HYPE_Processing
 * http:
 *
 * Copyright (c) 2013 Joshua Davis & James Cruz
 *
 * Distributed under the BSD License. See LICENSE.txt for details.
 *
 * All rights reserved.
 */
/**
 * A static class that provides some math methods.
 *
 * These methods are primarily used internally by the HYPE classes, but these
 * can also be used externally. (e.g. `randomInt()` can be very handy in some
 * sketches.)
 *
 * Most of these methods are here because any of the following:
 * - It's only available as a static PApplet method. (We are trying to avoid it
 *   due to Processing.js\u00canot having a static PApplet object, forcing us to call
 *   the math methods in a non-static context.)
 * - It's not available in _both_ `java.lang.Math` and the standard Javascript
 *   `Math` object.
 * - It's a modification of an existing method.
 * - It's not available at all in any of the above classes.
 *
 * @author james
 */
public static class HMath implements HConstants {
	private static boolean _usingTempSeed;
	private static int _resetSeedValue;
	public static float dist(float x1, float y1, float x2, float y2) {
		float w = x2 - x1;
		float h = y2 - y1;
		return (float) Math.sqrt(w*w + h*h);
	}
	public static float[] rotatePointArr(float x, float y, float rad) {
		float[] pt = new float[2];
		float c = (float) Math.cos(rad);
		float s = (float) Math.sin(rad);
		pt[0] = x*c - y*s;
		pt[1] = x*s + y*c;
		return pt;
	}
	public static PVector rotatePoint(float x, float y, float rad) {
		float[] f = rotatePointArr(x,y,rad);
		return new PVector(f[0], f[1]);
	}
	public static float yAxisAngle(float x1, float y1, float x2, float y2) {
		return (float) Math.atan2(x2-x1, y2-y1);
	}
	public static float xAxisAngle(float x1, float y1, float x2, float y2) {
		return (float) Math.atan2(y2-y1, x2-x1);
	}
	public static float[] absLocArr(HDrawable ref, float relX, float relY) {
		float[] f = {relX, relY, 0};
		while(ref != null) {
			float rot = ref.rotationRad();
			float[] g = rotatePointArr(f[0], f[1], rot);
			f[0] = g[0] + ref.x();
			f[1] = g[1] + ref.y();
			f[2] += rot;
			ref = ref.parent();
		}
		return f;
	}
	public static PVector absLoc(HDrawable ref, float relX, float relY) {
		float[] f = absLocArr(ref,relX,relY);
		return new PVector(f[0], f[1]);
	}
	public static PVector absLoc(HDrawable d) {
		return absLoc(d,0,0);
	}
	public static float[] relLocArr(HDrawable ref, float absX, float absY) {
		float[] f = absLocArr(ref,0,0);
		return rotatePointArr(absX-f[0], absY-f[1], -f[2]);
	}
	public static PVector relLoc(HDrawable ref, float absX, float absY) {
		float[] f = relLocArr(ref,absX,absY);
		return new PVector(f[0], f[1]);
	}
	public static int quadrant(float cx, float cy, float x, float y) {
		return (y>=cy)? (x>=cx? 1 : 2) : (x>=cx? 4 : 3);
	}
	public static int quadrant(float dcx, float dcy) {
		return (dcy>=0)? (dcx>=0? 1 : 2) : (dcx>=0? 4 : 3);
	}
	public static float ellipseRadius(float a, float b, float deg) {
		return ellipseRadiusRad(a,b, deg * D2R);
	}
	public static float ellipseRadiusRad(float a, float b, float rad) {
		float cosb = b * (float)Math.cos(rad);
		float sina = a * (float)Math.sin(rad);
		return a*b / (float)Math.sqrt(cosb*cosb + sina*sina);
	}
	public static PVector ellipsePoint(
			float cx, float cy, float a, float b, float deg
	) {
		return ellipsePointRad(cx, cy, a, b, deg*D2R);
	}
	public static PVector ellipsePointRad(
		float cx, float cy, float a, float b, float rad
	) {
		float[] f = ellipsePointRadArr(cx,cy, a,b, rad);
		return new PVector(f[0], f[1]);
	}
	public static float[] ellipsePointRadArr(
		float cx, float cy, float a, float b, float rad
	) {
		float[] f = new float[3];
		f[2] = ellipseRadiusRad(a, b, rad);
		f[0] = f[2] * (float)Math.cos(rad) + cx;
		f[1] = f[2] * (float)Math.sin(rad) + cy;
		return f;
	}
	public static float normalizeAngle(float deg) {
		return normalizeAngleRad(deg * D2R) * R2D;
	}
	public static float normalizeAngleRad(float rad) {
		rad %= PConstants.TWO_PI;
		if(rad < -PConstants.PI) rad += PConstants.TWO_PI;
		else if(rad > PConstants.PI) rad -= PConstants.TWO_PI;
		return rad;
	}
	public static float normalizeAngle2(float deg) {
		return normalizeAngleRad2(deg * D2R) * R2D;
	}
	public static float normalizeAngleRad2(float rad) {
		float norm = rad % PConstants.TWO_PI;
		if(norm < 0) norm += PConstants.TWO_PI;
		return norm;
	}
	public static float squishAngle(float w, float h, float deg) {
		return squishAngle(w, h, deg * D2R) * R2D;
	}
	public static float squishAngleRad(float w, float h, float rad) {
		float dx = (float)Math.cos(rad) * w/h;
		float dy = (float)Math.sin(rad);
		return (float) Math.atan2(dy,dx);
	}
	public static float lineSide(
		float x1, float y1, float x2, float y2, float ptx, float pty
	) {
		return (x2-x1)*(pty-y1) - (y2-y1)*(ptx-x1);
	}
	public static boolean collinear(
		float x1, float y1, float x2, float y2, float ptx, float pty
	) {
		return (lineSide(x1,y1, x2,y2, ptx,pty) == 0);
	}
	public static boolean leftOfLine(
		float x1, float y1, float x2, float y2, float ptx, float pty
	) {
		return (lineSide(x1,y1, x2,y2, ptx,pty) < 0);
	}
	public static boolean rightOfLine(
		float x1, float y1, float x2, float y2, float ptx, float pty
	) {
		return (lineSide(x1,y1, x2,y2, ptx,pty) > 0);
	}
	/**
	 * Solves for the real roots of a cubic equation with the given coefficients.
	 *
	 * Said equation is in the standard polynomial form:
	 *
	 *     ax^3 + bx^2 + cx + d = 0
	 *
	 * where `a`, `b`, `c` & `d` are the coefficients of said equation.
	 *
	 * Since the maximum amount of real roots that a cubic equation can have is
	 * three, the `roots` array is expected to have a size of at least 3.
	 *
	 * If this equation happens to be a straight line lying on the x-axis, then
	 * this method will return `-1`, meaning that there are infinite number of
	 * roots for this equation.
	 *
	 * This method may delegate itself to `solveQuadratic()` if `a` equals 0
	 * (in other words, the equation is quadratic.)
	 *
	 * The code for this method is heavily based from paper.js (MIT License)
	 * which in turn is based from the Uintah Library (also MIT License).
	 *
	 * @see solveQuadratic(float,float,float,float[])
	 * @param a    The coefficient for the first term (ax^3)
	 * @param b    The coefficient for the second term (bx^2)
	 * @param c    The coefficient for the third term (cx)
	 * @param d    The coefficient for the fourth term (d)
	 * @param roots    The array that will contain the roots of the equation
	 * @return The number of roots of the given equation
	 */
	public static int solveCubic(
		float a, float b, float c, float d, float[] roots
	) {
		if(Math.abs(a) < EPSILON) return solveQuadratic(b,c,d,roots);
		b /= a;
		c /= a;
		d /= a;
		float bb = b*b;
		float p = (bb - 3*c) / 9f;
		float ppp = p*p*p;
		float q = (2*bb*b - 9*b*c + 27*d) / 54;
		float D = q*q - ppp;
		b /= 3f;
		if(Math.abs(D) < EPSILON) {
			if(Math.abs(q) < EPSILON) {
				roots[0] = -b;
				return 1;
			}
			float sqrtp = (float)Math.sqrt(p);
			float signq = (q>0)? 1 : -1;
			roots[0] = -signq*2*sqrtp - b;
			roots[1] = signq*sqrtp - b;
			return 2;
		}
		if(D < 0) {
			float sqrtp = (float)Math.sqrt(p);
			float phi = (float)Math.acos(q / (sqrtp*sqrtp*sqrtp)) / 3;
			float t = -2*sqrtp;
			float o = PConstants.TWO_PI/3f;
			roots[0] = t*(float)Math.cos(phi) - b;
			roots[1] = t*(float)Math.cos(phi + o) - b;
			roots[2] = t*(float)Math.cos(phi - o) - b;
			return 3;
		}
		float A = (q>0?-1:1) *
			(float)Math.pow(Math.abs(q) + Math.sqrt(D), 1.0f/3.0f);
		roots[0] = A + p/A - b;
		return 1;
	}
	/**
	 * Solves for the real roots of a quadratic equation with the given
	 * coefficients.
	 *
	 * Said equation is in the standard polynomial form:
	 *
	 *     ax^2 + bx + c = 0
	 *
	 * where `a`, `b` & `c` are the coefficients of said equation.
	 *
	 * Since the maximum amount of roots that a quadratic equation can have is
	 * two, the `roots` array is expected to have a size of at least 2.
	 *
	 * If this equation happens to be a straight line lying on the x-axis, then
	 * this method will return `-1`, meaning that there are infinite number of
	 * roots for this equation.
	 *
	 * The code for this method is heavily based from paper.js (MIT License)
	 * which in turn is based from the Uintah Library (also MIT License).
	 *
	 * @see solveCubic(float,float,float,float,float[])
	 * @param a    The coefficient for the first term (`ax^2`)
	 * @param b    The coefficient for the second term (`bx`)
	 * @param c    The coefficient for the third term (`c`)
	 * @param roots    The array that will contain the roots of the equation
	 * @return The number of roots of the given equation
	 */
	public static int solveQuadratic(float a, float b, float c, float[] roots) {
		if(Math.abs(a) < EPSILON) {
			if(Math.abs(b) >= EPSILON) {
				roots[0] = -c/b;
				return 1;
			}
			return (Math.abs(c)<EPSILON)? -1 : 0;
		}
		float q = b*b - 4*a*c;
		if(q < 0) return 0;
		q = (float)Math.sqrt(q);
		a *= 2;
		int numRoots = 0;
		roots[numRoots++] = (-b-q) / a;
		if(q > 0) roots[numRoots++] = (-b+q) / a;
		return numRoots;
	}
	/**
	 * Solves for the valid parameters of a given cubic bezier equation.
	 *
	 * This method calls `solveCubic()` to solve for the said equation.
	 *
	 * Since cubic bezier curves can have at most three parameters that could
	 * give the same value, the `params` array is expected to have a size of at
	 * least 3. Note that these parameters can be less than `0` or more than
	 * `1`, so you will need to check them before using the contents of the
	 * `params` array.
	 *
	 * The `val` argument is the value to be tested, where:
	 *
	 *     val = bezier(p0,p1,p2,p3,param)
	 *
	 * If the above equation is in such a way that it could not be true, then
	 * this method will return `0` valid parameters.
	 *
	 * @see solveCubic(float,float,float,float,float[]), bezierParam(float,float,float,float,float[])
	 * @param p0    The first anchor point
	 * @param p1    The first control point
	 * @param p2    The second control point
	 * @param p3    The second anchor point
	 * @param val   The value to be tested with the curve
	 * @param params    The array that will contain the parameters of the bezier equation
	 * @return The number of valid parameters of the given bezier equation
	 */
	public static int bezierParam(
		float p0, float p1, float p2, float p3,
		float val, float[] params
	) {
		float max = p0;
		if(max < p1) max = p1;
		if(max < p2) max = p2;
		if(max < p3) max = p3;
		float min = p0;
		if(min > p1) min = p1;
		if(min > p2) min = p2;
		if(min > p3) min = p3;
		if(val<min || val>max) return 0;
		float a = 3*(p1-p2) - p0 + p3;
		float b = 3*(p0 - 2*p1 + p2);
		float c = 3*(p1-p0);
		float d = p0 - val;
		return solveCubic(a,b,c,d,params);
	}
	public static int bezierParam(
		float p0, float p1, float p2,
		float val, float[] params
	) {
		float max = p0;
		if(max < p1) max = p1;
		if(max < p2) max = p2;
		float min = p0;
		if(min > p1) min = p1;
		if(min > p2) min = p2;
		if(val<min || val>max) return 0;
		float a = p2 - 2*p1 + p0;
		float b = 2 * (p1-p0);
		float c = p0 - val;
		return solveQuadratic(a,b,c,params);
	}
	public static float bezierPoint(
		float p0, float p1, float p2, float p3, float t
	) {
		float tt = t*t;
		float a = 3*(p1-p2) - p0 + p3;
		float b = 3*(p0 - 2*p1 + p2);
		float c = 3*(p1-p0);
		return a*tt*t + b*tt + c*t + p0;
	}
	public static float bezierPoint(float p0, float p1, float p2, float t) {
		float a = p2 - 2*p1 + p0;
		float b = 2 * (p1-p0);
		return a*t*t + b*t + p0;
	}
	public static float bezierTangent(
		float p0, float p1, float p2, float p3, float t
	) {
		float a = 3 * (3*(p1-p2) - p0 + p3);
		float b = 6 * (p0 - 2*p1 + p2);
		float c = 3 * (p1-p0);
		return a*t*t + b*t + c;
	}
	public static float bezierTangent(float p0, float p1, float p2, float t) {
		float a = 2 * (p2 - 2*p1 + p0);
		float b = 2 * (p1-p0);
		return a*t + b;
	}
	public static int randomInt(float high) {
		return (int) Math.floor( H.app().random(high) );
	}
	public static int randomInt(float low, float high) {
		return (int) Math.floor( H.app().random(low,high) );
	}
	public static int randomInt32() {
		return randomInt(-2147483648,2147483647);
	}
	public static void tempSeed(long seed) {
		if(!_usingTempSeed) {
			_resetSeedValue = randomInt32();
			_usingTempSeed = true;
		}
		H.app().randomSeed(seed);
	}
	public static void removeTempSeed() {
		H.app().randomSeed(_resetSeedValue);
	}
	public static float sineWave(float stepDegrees) {
		return (float) Math.sin(stepDegrees * H.D2R);
	}
	public static float triangleWave(float stepDegrees) {
		float outVal = (stepDegrees % 180) / 90;
		if(outVal > 1)
			outVal = 2-outVal;
		if(stepDegrees % 360 > 180)
			outVal = -outVal;
		return outVal;
	}
	public static float sawWave(float stepDegrees) {
		float outVal = (stepDegrees % 180) / 180;
		if(stepDegrees % 360 >= 180)
			outVal -= 1;
		return outVal;
	}
	public static float squareWave(float stepDegrees) {
		return (stepDegrees % 360 > 180)? -1 : 1;
	}
	public static boolean hasBits(byte target, byte mask) {
		return (target&mask) == mask;
	}
	public static boolean hasBits(int target, int mask) {
		return (target&mask) == mask;
	}
	public static byte setBits(byte target, byte mask, boolean val) {
		return (byte) (val? target|mask : target&(~mask));
	}
	public static int setBits(int target, int mask, boolean val) {
		return (val)? target|mask : target&(~mask);
	}
	public static boolean lessThan(float a, float b, float tolerance) {
		return a < b + tolerance;
	}
	public static boolean lessThan(float a, float b) {
		return a < b + TOLERANCE;
	}
	public static boolean greaterThan(float a, float b, float tolerance) {
		return b < a + tolerance;
	}
	public static boolean greaterThan(float a, float b) {
		return b < a + TOLERANCE;
	}
	public static boolean isEqual(float a, float b, float tolerance) {
		return Math.abs(a-b) < tolerance;
	}
	public static boolean isEqual(float a, float b) {
		return Math.abs(a-b) < TOLERANCE;
	}
	public static boolean isZero(float a, float tolerance) {
		return Math.abs(a) < tolerance;
	}
	public static boolean isZero(float a) {
		return Math.abs(a) < TOLERANCE;
	}
	public static float map(float val,
		float start1, float stop1, float start2, float stop2
	) {
		return start2 + (stop2-start2) * (val-start1)/(stop1-start1);
	}
	public static float round512(float val) {
		return Math.round(val*512)/512f;
	}
}
/*
 * HYPE_Processing
 * http:
 * 
 * Copyright (c) 2013 Joshua Davis & James Cruz
 * 
 * Distributed under the BSD License. See LICENSE.txt for details.
 * 
 * All rights reserved.
 */
public static class HMouse implements HLocatable {
	private PApplet _app;
	private int _button;
	private boolean _started, _moved;
	public HMouse(PApplet app) {
		_app = app;
	}
	public boolean started() {
		return _started;
	}
	public boolean moved() {
		return _moved;
	}
	public int button() {
		return _button;
	}
	public void handleEvents() {
		_button = _app.mouseButton;
		if(!_moved) _moved = (_app.pmouseX != 0) || (_app.pmouseY != 0);
		else if(!_started) _started = true;
	}
	public float x() {
		return _app.mouseX;
	}
	public HMouse x(float newX) {
		return this;
	}
	public float y() {
		return _app.mouseY;
	}
	public HMouse y(float newY) {
		return this;
	}
	public float z() {
		return 0;
	}
	public HMouse z(float newZ) {
		return this;
	}
}
/*
 * HYPE_Processing
 * http:
 * 
 * Copyright (c) 2013 Joshua Davis & James Cruz
 * 
 * Distributed under the BSD License. See LICENSE.txt for details.
 * 
 * All rights reserved.
 */
public static abstract class HNode<T extends HNode<T>> {
	protected T _prev, _next;
	public T prev() {
		return _prev;
	}
	public T next() {
		return _next;
	}
	public boolean poppedOut() {
		return (_prev==null) && (_next==null);
	}
	public void popOut() {
		if(_prev!=null) _prev._next = _next;
		if(_next!=null) _next._prev = _prev;
		_prev = _next = null;
	}
	public void putBefore(T dest) {
		if(dest==null || dest.equals(this)) return;
		if(!poppedOut()) popOut();
		T p = dest._prev;
		if(p!=null) p._next = (T) this;
		_prev = p;
		_next = dest;
		dest._prev = (T) this;
	}
	public void putAfter(T dest) {
		if(dest==null || dest.equals(this)) return;
		if(!poppedOut()) popOut();
		T n = dest.next();
		dest._next = (T) this;
		_prev = dest;
		_next = n;
		if(n!=null) n._prev = (T) this;
	}
	public void replaceNode(T dest) {
		if(dest==null || dest.equals(this)) return;
		if(!poppedOut()) popOut();
		T p = dest._prev;
		T n = dest._next;
		dest._prev = dest._next = null;
		_prev = p;
		_next = n;
	}
	public void swapLeft() {
		if(_prev==null) return;
		T pairPrev = _prev._prev;
		T pairNext = _next;
		_next = _prev;
		_prev._prev = (T) this;
		_prev._next = pairNext;
		if(pairNext != null) pairNext._prev = _prev;
		_prev = pairPrev;
		if(pairPrev != null) pairPrev._next = (T) this;
	}
	public void swapRight() {
		if(_next==null) return;
		T pairPrev = _prev;
		T pairNext = _next._next;
		_next._next = (T) this;
		_prev = _next;
		_next._prev = pairPrev;
		if(pairPrev != null) pairPrev._next = _next;
		_next = pairNext;
		if(pairNext != null) pairNext._prev = (T) this;
	}
}
/*
 * HYPE_Processing
 * http:
 * 
 * Copyright (c) 2013 Joshua Davis & James Cruz
 * 
 * HOrbiter3D
 * by Benjamin Fox / twitter.com/tracerstar / github.com/tracerstar
 * 
 * Distributed under the BSD License. See LICENSE.txt for details.
 * 
 * All rights reserved.
 */
public static class HOrbiter3D extends HBehavior {
	private HDrawable _target;
	private float _radius, _ySpeed, _zSpeed;
	private float _yAngle = 0;
	private float _zAngle = 0;
	private float _startX, _startY, _startZ;
	private float _targetX, _targetY, _targetZ;
	private float s, t;
	private HOrbiter3D _orbit = null;
	public HOrbiter3D() {
		_startX = 0;
		_startY = 0;
		_startZ = 0;
		_radius = 250;
		_ySpeed = 0;
		_zSpeed = 1;
		register();
	}
	public HOrbiter3D(float x, float y, float z) {
		_startX = x;
		_startY = y;
		_startZ = z;
		_radius = 250;
		_ySpeed = 0;
		_zSpeed = 1;
		register();
	}
	public HOrbiter3D target(HDrawable d) {
		_target = d;
		return this;
	}
	public HDrawable target() {
		return _target;
	}
	public float x() {
		return _targetX;
	}
	public float y() {
		return _targetY;
	}
	public float z() {
		return _targetZ;
	}
	public HOrbiter3D parent(HOrbiter3D h) {
		_orbit = h;
		return this;
	}
	public HOrbiter3D parent() {
		return _orbit;
	}
	public HOrbiter3D startX(float f) {
		_startX = f;
		return this;
	}
	public HOrbiter3D startY(float f) {
		_startY = f;
		return this;
	}
	public HOrbiter3D startZ(float f) {
		_startZ = f;
		return this;
	}	
	public float startX() {
		return _startX;
	}
	public float startY() {
		return _startY;
	}
	public float startZ() {
		return _startZ;
	}
	public HOrbiter3D ySpeed(float f) {
		_ySpeed = f;
		return this;
	}
	public HOrbiter3D zSpeed(float f) {
		_zSpeed = f;
		return this;
	}
	public float ySpeed() {
		return _ySpeed;
	}
	public float zSpeed() {
		return _zSpeed;
	}
	public HOrbiter3D yAngle(float f) {
		_yAngle = f;
		return this;
	}
	public HOrbiter3D zAngle(float f) {
		_zAngle = f;
		return this;
	}
	public float yAngle() {
		return _yAngle;
	}
	public float zAngle() {
		return _zAngle;
	}
	public HOrbiter3D radius(float f) {
		_radius = f;
		return this;
	}
	public float radius() {
		return _radius;
	}
	public void _run() {
		if (_orbit != null) {
			_orbit._run();
			_startX = _orbit.x();
			_startY = _orbit.y();
			_startZ = _orbit.z();
		}
		s = radians(_yAngle);
		t = radians(_zAngle);
		_targetX = _radius * cos(s) * sin(t) + _startX;
		_targetY = _radius * sin(s) * sin(t) + _startY;
		_targetZ = _radius * cos(t) + _startZ;
		_yAngle += _ySpeed;
		_zAngle += _zSpeed;
	}
	public void runBehavior(PApplet app) {
		if(_target==null) return;
		_run();
		_target.x(_targetX);
		_target.y(_targetY);
		_target.z(_targetZ);
	}
	public HOrbiter3D register() {
		return (HOrbiter3D) super.register();
	}
	public HOrbiter3D unregister() {
		return (HOrbiter3D) super.unregister();
	}
}
/*
 * HYPE_Processing
 * http:
 * 
 * Copyright (c) 2013 Joshua Davis & James Cruz
 * 
 * Distributed under the BSD License. See LICENSE.txt for details.
 * 
 * All rights reserved.
 */
public static class HOscillator extends HBehavior {
	private HDrawable _target;
	private float _min1, _min2, _min3;
	private float _rel1, _rel2, _rel3;
	private float _max1, _max2, _max3;
	private float _curr1, _curr2, _curr3;
	private float _map1, _map2, _map3;
	private float _origw, _origh, _origd;
	private float _step, _speed, _freq;
	private float _clip_min, _clip_max;
	private float _offset;
	private boolean _clip;
	private int _property, _waveform, _tick;
	private float _startStep;
	private ArrayList<HOscillator> _amplifiers, _reducers;
	public HOscillator() {
		_clip = false;
		_speed = _freq = 1;
		_waveform = HConstants.SINE;
		_property = HConstants.Y;
		_amplifiers = new ArrayList<HOscillator>();
		_reducers = new ArrayList<HOscillator>();
		_offset = 0;
		_tick = 0;
		_startStep = 0;
		register();
	}
	public HOscillator addAmplifier(HOscillator wave) {
		_amplifiers.add(wave);
		return this;
	}
	public HOscillator addReducer(HOscillator wave) {
		_reducers.add(wave);
		return this;
	}
	public HOscillator clipping(float clipMin, float clipMax) {
		_clip = true;
		_clip_min = clipMin;
		_clip_max = clipMax;
		return this;
	}
	/** @deprecated */
	public HOscillator createCopy() {
		HOscillator copy = new HOscillator();
		copy._min1 = _min1;
		copy._min2 = _min2;
		copy._min3 = _min3;
		copy._max1 = _max1;
		copy._max2 = _max2;
		copy._max3 = _max3;
		copy._rel1 = _rel1;
		copy._rel2 = _rel2;
		copy._rel3 = _rel3;
		copy._origw = _origw;
		copy._origh = _origh;
		copy._origd = _origd;
		copy._step = _step;
		copy._speed = _speed;
		copy._freq = _freq;
		copy._property = _property;
		copy._waveform = _waveform;
		return copy;
	}
	public HOscillator target(HDrawable d) {
		_target = d;
		if(d != null) {
			_origw = d.width();
			_origh = d.height();
			_origd = 0;
		}
		return this;
	}
	public HOscillator target(HDrawable3D d) {
		_target = d;
		if(d != null) {
			_origw = d.width();
			_origh = d.height();
			_origd = d.depth();
		}
		return this;
	}	
	public HDrawable target() {
		return _target;
	}
	public HOscillator currentStep(float stepDegrees) {
		_step = stepDegrees;
		_startStep = stepDegrees;
		return this;
	}
	public float currentStep() {
		return _step;
	}
	public HOscillator synchStep(float stepDegrees, float tick) {
		currentStep(stepDegrees + tick * _speed);
		return this;
	}
	public HOscillator offset(float offset) {
		_offset = offset;
		return this;
	}
	public HOscillator speed(float f) {
		_speed = f;
		return this;
	}
	public float speed() {
		return _speed;
	}
	public HOscillator range(float minimum, float maximum) {
		return min(minimum).max(maximum);
	}
	public HOscillator range(
		float minA, float minB,
		float maxA, float maxB
	) {
		return min(minA,minB).max(maxA,maxB);
	}
	public HOscillator range(
		float minA, float minB, float minC,
		float maxA, float maxB, float maxC
	) {
		return min(minA,minB,minC).max(maxA,maxB,maxC);
	}
	public HOscillator min(float a) {
		if (_target instanceof HDrawable3D) {
			return min(a,a,a);
		} else {
			return min(a,a,0);
		}
	}
	public HOscillator min(float a, float b) {
		return min(a,b,0);
	}
	public HOscillator min(float a, float b, float c) {
		_min1 = a;
		_min2 = b;
		_min3 = c;
		return this;
	}
	public float min() {
		return _min1;
	}
	public float min1() {
		return _min1;
	}
	public float min2() {
		return _min2;
	}
	public float min3() {
		return _min3;
	}
	public HOscillator relativeVal(float a) {
		return relativeVal(a,a);
	}
	public HOscillator relativeVal(float a, float b) {
		return relativeVal(a,b,0);
	}
	public HOscillator relativeVal(float a, float b, float c) {
		_rel1 = a;
		_rel2 = b;
		_rel3 = c;
		return this;
	}
	public float relativeVal() {
		return _rel1;
	}
	public float relativeVal1() {
		return _rel1;
	}
	public float relativeVal2() {
		return _rel2;
	}
	public float relativeVal3() {
		return _rel3;
	}
	public HOscillator max(float a) {
		if (_target instanceof HDrawable3D) {
			return max(a,a,a);
		} else {
			return max(a,a,0);
		}
	}
	public HOscillator max(float a, float b) {
		return max(a,b,0);
	}
	public HOscillator max(float a, float b, float c) {
		_max1 = a;
		_max2 = b;
		_max3 = c;
		return this;
	}
	public float max() {
		return _max1;
	}
	public float max1() {
		return _max1;
	}
	public float max2() {
		return _max2;
	}
	public float max3() {
		return _max3;
	}
	public HOscillator freq(float f) {
		_freq = f;
		return this;
	}
	public float freq() {
		return _freq;
	}
	public HOscillator property(int id) {
		_property = id;
		return this;
	}
	public int property() {
		return _property;
	}
	public HOscillator waveform(int waveformId) {
		_waveform = waveformId;
		return this;
	}
	public int waveform() {
		return _waveform;
	}
	public float nextRaw() {
		float deg = (_step*_freq) % 360;
		float rawVal;
		switch(_waveform) {
		case HConstants.SINE:		rawVal = HMath.sineWave(deg); break;
		case HConstants.TRIANGLE:	rawVal = HMath.triangleWave(deg); break;
		case HConstants.SAW:		rawVal = HMath.sawWave(deg); break;
		case HConstants.SQUARE:		rawVal = HMath.squareWave(deg); break;
		default: rawVal = 0; break;
		}
		_map1 = HMath.map(rawVal, -1,1, _min1,_max1);
		_map2 = HMath.map(rawVal, -1,1, _min2,_max2);
		_map3 = HMath.map(rawVal, -1,1, _min3,_max3);
		if (_amplifiers.size() > 0) {
			for (int i = _amplifiers.size()-1; i >= 0; i--) {
				HOscillator amplifier = _amplifiers.get(i);
				amplifier.synchStep(_startStep, _tick);
				amplifier.nextRaw();
				_map1 += amplifier.map1();
				_map2 += amplifier.map2();
				_map3 += amplifier.map3();
			}
		}
		if (_reducers.size() > 0) {
			for (int i = _reducers.size()-1; i >= 0; i--) {
				HOscillator reducer = _reducers.get(i);
				reducer.synchStep(_startStep, _tick);
				reducer.nextRaw();
				_map1 -= reducer.map1();
				_map2 -= reducer.map2();
				_map3 -= reducer.map3();
			}
		}
		if (_clip) {
			if (_map1 > _clip_max) {
				_map1 = _clip_max;
			}
			if (_map2 > _clip_max) {
				_map2 = _clip_max;
			}
			if (_map3 > _clip_max) {
				_map3 = _clip_max;
			}
			if (_map1 < _clip_min) {
				_map1 = _clip_min;
			}
			if (_map2 < _clip_min) {
				_map2 = _clip_min;
			}
			if (_map3 < _clip_min) {
				_map3 = _clip_min;
			}
		}
		_map1 += _offset;
		_map2 += _offset;
		_map3 += _offset;
		_curr1 = _map1 + _rel1;
		_curr2 = _map2 + _rel2;
		_curr3 = _map3 + _rel3;
		_step += _speed;
		_tick++;
		return rawVal;
	}
	public float curr() {
		return _curr1;
	}
	public float curr1() {
		return _curr1;
	}
	public float curr2() {
		return _curr2;
	}
	public float curr3() {
		return _curr3;
	}
	public float map1() {
		return _map1;
	}
	public float map2() {
		return _map2;
	}
	public float map3() {
		return _map3;
	}
	public void runBehavior(PApplet app) {
		if(_target==null) return;
		nextRaw();
		float v1 = _curr1;
		float v2 = _curr2;
		float v3 = _curr3;
		switch(_property) {
		case HConstants.WIDTH:		_target.width(v1); break;
		case HConstants.HEIGHT:		_target.height(v1); break;
		case HConstants.SCALE:
			v1 *= _origw;
			v2 *= _origh;
			v3 *= _origd;
		case HConstants.SIZE: 		_target.size(new PVector(v1, v2, v3)); break;
		case HConstants.ALPHA:		_target.alpha(Math.round(v1)); break;
		case HConstants.X:			_target.x(v1); break;
		case HConstants.Y:			_target.y(v1); break;
		case HConstants.Z:			_target.z(v1); break;
		case HConstants.LOCATION:	_target.loc(v1,v2,v3); break;
		case HConstants.ROTATIONX:	_target.rotationX(v1); break;
		case HConstants.ROTATIONY:	_target.rotationY(v1); break;
		case HConstants.ROTATIONZ:	_target.rotationZ(v1); break;
		case HConstants.DROTATIONX:	_target.rotateX(v1); break;
		case HConstants.DROTATIONY:	_target.rotateY(v1); break;
		case HConstants.DROTATIONZ:	_target.rotateZ(v1); break;
		case HConstants.DX:			_target.move(v1,0); break;
		case HConstants.DY:			_target.move(0,v1); break;
		case HConstants.DLOC:		_target.move(v1,v1); break;
		default: break;
		}
	}
	public HOscillator register() {
		return (HOscillator) super.register();
	}
	public HOscillator unregister() {
		return (HOscillator) super.unregister();
	}
}
/*
 * HYPE_Processing
 * http:
 * 
 * Copyright (c) 2013 Joshua Davis & James Cruz
 * 
 * Distributed under the BSD License. See LICENSE.txt for details.
 * 
 * All rights reserved.
 */
public static class HPath extends HDrawable {
	public static final int HANDLE_FILL = 0xFFFF0000;
	public static final int HANDLE_STROKE = 0xFF202020;
	public static final float HANDLE_STROKE_WEIGHT = 1;
	public static final float HANDLE_SIZE = 6;
	private ArrayList<HVertex> _vertices;
	private int _mode;
	private boolean _drawsHandles;
	public HPath() {
		this(PConstants.PATH);
	}
	public HPath(int modeId) {
		_mode = modeId;
		_vertices = new ArrayList<HVertex>();
	}
	public HPath createCopy() {
		HPath copy = new HPath(_mode);
		copy.copyPropertiesFrom(this);
		copy._drawsHandles = _drawsHandles;
		for(int i=0; i<numVertices(); ++i) {
			copy._vertices.add(vertex(i).createCopy(copy));
		}
		return copy;
	}
	public HPath mode(int modeId) {
		_mode = modeId;
		return this;
	}
	public int mode() {
		return _mode;
	}
	public HPath drawsHandles(boolean b) {
		_drawsHandles = b;
		return this;
	}
	public boolean drawsHandles() {
		return _drawsHandles;
	}
	public int numVertices() {
		return _vertices.size();
	}
	public HVertex vertex(int index) {
		return _vertices.get(index);
	}
	public HPath vertex(float x, float y) {
		_vertices.add(new HVertex(this).set(x,y));
		return this;
	}
	public HPath vertex(float cx, float cy, float x, float y) {
		_vertices.add(new HVertex(this).set(cx,cy, x,y));
		return this;
	}
	public HPath vertex(
		float cx1, float cy1,
		float cx2, float cy2,
		float x, float y
	) {
		_vertices.add(new HVertex(this).set(cx1,cy1, cx2,cy2, x,y));
		return this;
	}
	public HPath vertexUV(float u, float v) {
		_vertices.add(new HVertex(this).setUV(u,v));
		return this;
	}
	public HPath vertexUV(float cu, float cv, float u, float v) {
		_vertices.add(new HVertex(this).setUV(cu,cv, u,v));
		return this;
	}
	public HPath vertexUV(
		float cu1, float cv1,
		float cu2, float cv2,
		float u, float v
	) {
		_vertices.add(new HVertex(this).setUV(cu1,cv1, cu2,cv2, u,v));
		return this;
	}
	public HPath adjust() {
		int numv = numVertices();
		float[] minmax = new float[4];
		for(int i=0; i<numv; ++i) vertex(i).computeMinMax(minmax);
		float offU = -minmax[0],	offV = -minmax[1];
		float oldW = _width,		oldH = _height;
		anchorUV(offU,offV).scale(minmax[2]+offU, minmax[3]+offV);
		for(int i=0; i<numv; ++i) vertex(i).adjust(offU,offV, oldW,oldH);
		return this;
	}
	public HPath endPath() {
		return adjust();
	}
	public HPath reset() {
		size(DEFAULT_WIDTH,DEFAULT_HEIGHT).anchorUV(0,0);
		return clear();
	}
	public HPath beginPath(int modeId) {
		return reset().mode(modeId);
	}
	public HPath beginPath() {
		return reset();
	}
	public HPath clear() {
		_vertices.clear();
		return this;
	}
	public HPath line(float x1, float y1, float x2, float y2) {
		return beginPath(PConstants.PATH)
				.vertex(x1,y1).vertex(x2,y2).endPath();
	}
	public HPath lineUV(float u1, float v1, float u2, float v2) {
		return beginPath(PConstants.PATH)
			.vertexUV(u1,v1).vertexUV(u2,v2).endPath();
	}
	public HPath triangle(int type, int direction) {
		clear().mode(PConstants.POLYGON);
		float ratio = 2;
		switch(type) {
		case HConstants.EQUILATERAL:
			ratio = (float) Math.sin(PConstants.TWO_PI/6);
			break;
		case HConstants.RIGHT:
			ratio = (float) Math.sin(PConstants.TWO_PI/8)/HConstants.SQRT2;
			break;
		}
		switch(direction) {
		case HConstants.TOP: case HConstants.CENTER_TOP:
			vertexUV(.5f,0).vertexUV(0,1).vertexUV(1,1);
			if(ratio < 2) height(_width*ratio).proportional(true);
			break;
		case HConstants.BOTTOM: case HConstants.CENTER_BOTTOM:
			vertexUV(.5f,1).vertexUV(1,0).vertexUV(0,0);
			if(ratio < 2) height(_width*ratio).proportional(true);
			break;
		case HConstants.RIGHT: case HConstants.CENTER_RIGHT:
			vertexUV(1,.5f).vertexUV(0,0).vertexUV(0,1);
			if(ratio < 2) width(_height*ratio).proportional(true);
			break;
		case HConstants.LEFT: case HConstants.CENTER_LEFT:
			vertexUV(0,.5f).vertexUV(1,1).vertexUV(1,0);
			if(ratio < 2) width(_height*ratio).proportional(true);
			break;
		case HConstants.TOP_LEFT:
			vertexUV(0,0).vertexUV(0,1).vertexUV(1,0); break;
		case HConstants.TOP_RIGHT:
			vertexUV(1,0).vertexUV(0,0).vertexUV(1,1); break;
		case HConstants.BOTTOM_RIGHT:
			vertexUV(1,1).vertexUV(0,1).vertexUV(1,0); break;
		case HConstants.BOTTOM_LEFT:
			vertexUV(0,1).vertexUV(0,0).vertexUV(1,1); break;
		}
		return this;
	}
	public HPath polygon(int numEdges) {
		return polygonRad(numEdges, 0);
	}
	public HPath polygon(int numEdges, float startDeg) {
		return polygonRad(numEdges, startDeg*HConstants.D2R);
	}
	public HPath polygonRad(int numEdges, float startRad) {
		clear().mode(PConstants.POLYGON);
		float inc = PConstants.TWO_PI / numEdges;
		for(int i=0; i<numEdges; ++i) {
			float rad = startRad + inc*i;
			vertexUV(
				0.5f + 0.5f*(float)Math.cos(rad),
				0.5f + 0.5f*(float)Math.sin(rad));
		}
		return this;
	}
	public HPath star(int numEdges, float depth) {
		return starRad(numEdges, depth, 0);
	}
	public HPath star(int numEdges, float depth, float startDeg) {
		return starRad(numEdges, depth, startDeg*HConstants.D2R);
	}
	public HPath starRad(int numEdges, float depth, float startRad) {
		clear().mode(PConstants.POLYGON);
		float inc = PConstants.TWO_PI / numEdges;
		float idepth2 = (1-depth) * 0.5f;
		for(int i=0; i<numEdges; ++i) {
			float rad = startRad + inc*i;
			vertexUV(
				0.5f + 0.5f*(float)Math.cos(rad),
				0.5f + 0.5f*(float)Math.sin(rad));
			rad += inc/2;
			vertexUV(
				0.5f + idepth2*(float)Math.cos(rad),
				0.5f + idepth2*(float)Math.sin(rad));
		}
		return this;
	}
	public boolean containsRel(float relX, float relY) {
		int numv = numVertices();
		if(numv <= 0) return false;
		if(_width == 0) return (relX == 0) && (0<relY && relY<_height);
		if(_height == 0) return (relY == 0) && (0<relX && relX<_width);
		if( !super.containsRel(relX,relY) ) return false;
		boolean openPath = false;
		switch(_mode) {
		case PConstants.POINTS:
			for(int i=0; i<numv; ++i) {
				HVertex curr = vertex(i);
				if(curr.u()==relX/_width && curr.v()==relY/_height) return true;
			}
			return false;
		case PConstants.PATH:
			openPath = true;
			if(HColors.isTransparent(_fill)) {
				HVertex prev = vertex(openPath? 0 : numv-1);
				for(int i=(openPath? 1 : 0); i<numv; ++i) {
					HVertex curr = vertex(i);
					if(curr.inLine(prev,relX,relY)) return true;
					prev = curr;
					if(openPath) openPath = false;
				}
				return false;
			}
		default:
			float u = relX / _width; 
			float v = relY / _height; 
			boolean isIn = false;
			HVertex prev = vertex(numv-1);
			HVertex pprev = vertex(numv>1? numv-2 : 0);
			for(int i=0; i<numv; ++i) {
				HVertex curr = vertex(i);
				if(curr.intersectTest(pprev,prev, u,v, openPath)) isIn = !isIn;
				pprev = prev;
				prev = curr;
				if(openPath) openPath = false;
			}
			return isIn;
		}
	}
	public void draw( PGraphics g, boolean usesZ,
		float drawX, float drawY, float alphaPc
	) {
		int numv = numVertices();
		if(numv <= 0) return;
		applyStyle(g, alphaPc);
		boolean drawsLines = (_mode != PConstants.POINTS);
		boolean isPolygon = (_mode==PConstants.POLYGON && numv>2);
		boolean isSimple = true;
		if(drawsLines) g.beginShape();
		else g.beginShape(PConstants.POINTS);
		int itrs = (isPolygon)? numv+1 : numv;
		for(int i=0; i<itrs; ++i) {
			HVertex v = vertex(i<numv? i : 0);
			v.draw(g, drawX, drawY, isSimple);
			if(isSimple && drawsLines) isSimple = false;
		}
		if(isPolygon) g.endShape(PConstants.CLOSE);
		else g.endShape();
		if(_drawsHandles && drawsLines) {
			HVertex prev = vertex(isPolygon? numv-1 : 0);
			for(int i=(isPolygon? 0 : 1); i<numv; ++i) {
				HVertex curr = vertex(i);
				curr.drawHandles(g, prev, drawX, drawY);
				prev = curr;
			}
		}
	}
}
/*
 * HYPE_Processing
 * http:
 * 
 * Copyright (c) 2013 Joshua Davis & James Cruz
 * 
 * Distributed under the BSD License. See LICENSE.txt for details.
 * 
 * All rights reserved.
 */
public static class HPixelColorist implements HColorist, HImageHolder {
	private PImage img;
	private boolean fillFlag, strokeFlag;
	public HPixelColorist() {
		fillAndStroke();
	}
	public HPixelColorist(Object imgArg) {
		this();
		image(imgArg);
	}
	public HPixelColorist image(Object imgArg) {
		img = H.getImage(imgArg);
		return this;
	}
	public PImage image() {
		return img;
	}
	/** @deprecated */
	public HPixelColorist setImage(Object imgArg) {
		if(imgArg instanceof PImage) {
			img = (PImage) imgArg;
		} else if(imgArg instanceof HImage) {
			img = ((HImage) imgArg).image();
		} else if(imgArg instanceof String) {
			img = H.app().loadImage((String) imgArg);
		} else if(imgArg == null) {
			img = null;
		}
		return this;
	}
	/** @deprecated */
	public PImage getImage() {
		return img;
	}
	public int getColor(float x, float y) {
		return (img==null)? 0 : img.get(Math.round(x), Math.round(y));
	}
	public HPixelColorist fillOnly() {
		fillFlag = true;
		strokeFlag = false;
		return this;
	}
	public HPixelColorist strokeOnly() {
		fillFlag = false;
		strokeFlag = true;
		return this;
	}
	public HPixelColorist fillAndStroke() {
		fillFlag = strokeFlag = true;
		return this;
	}
	public boolean appliesFill() {
		return fillFlag;
	}
	public boolean appliesStroke() {
		return strokeFlag;
	}
	public HDrawable applyColor(HDrawable drawable) {
		int clr = getColor(drawable.x(), drawable.y());
		if(fillFlag)
			drawable.fill(clr);
		if(strokeFlag)
			drawable.stroke(clr);
		return drawable;
	}
}
/*
 * HYPE_Processing
 * http:
 * 
 * Copyright (c) 2013 Joshua Davis & James Cruz
 * 
 * based on old AS3 layout by Michael Svendsen / twitter.com/michaelSvendsen
 * 
 * Distributed under the BSD License. See LICENSE.txt for details.
 * 
 * All rights reserved.
 */
public static class HPolarLayout implements HLayout {
	private int _index;
	private float _length, _angleStep, _scaleMultiplier, _offsetX, _offsetY;
	private Boolean _scaleByDistance;
	public HPolarLayout() {
		_index = 0;
		_length = 1;
		_angleStep = 0.1f;
		_offsetX = 0;
		_offsetY = 0;
		_scaleByDistance = false;
		_scaleMultiplier = 0;
	}
	public HPolarLayout(float length, float angleStep) {
		_index = 0;
		_length = length;
		_angleStep = angleStep;
		_offsetX = 0;
		_offsetY = 0;
		_scaleByDistance = false;
		_scaleMultiplier = 0;
	}
	public HPolarLayout offset(float x, float y) {
		_offsetX = x;
		_offsetY = y;
		return this;
	}
	public HPolarLayout offsetX(float f) {
		_offsetX = f;
		return this;
	}
	public float offsetX() {
		return _offsetX;
	}
	public HPolarLayout offsetY(float f) {
		_offsetY = f;
		return this;
	}
	public float offsetY() {
		return _offsetY;
	}
	public HPolarLayout length(float f) {
		_length = f;
		return this;
	}
	public float length() {
		return _length;
	}
	public HPolarLayout angleStep(float f) {
		_angleStep = f;
		return this;
	}
	public float angleStep() {
		return _angleStep;
	}
	public HPolarLayout scale(float f) {
		_scaleByDistance = true;
		_scaleMultiplier = f;
		return this;
	}
	private void applyScale(HDrawable target) {
		float d = HMath.dist( _offsetX, _offsetY, target.x(), target.y() );
		target.scale(d * _scaleMultiplier);
	}
	public PVector getNextPoint() {
		PVector pt = new PVector();
		float r     = _index * _length;
		float theta = _index * _angleStep;
		pt.x = r * cos(theta) + _offsetX;
		pt.y = r * sin(theta) + _offsetY;
		++_index;
		return pt;
	}
	public void applyTo(HDrawable target) {
		target.loc(getNextPoint());
		if (_scaleByDistance == true) {
			applyScale(target);
		}
	}
}
/*
 * HYPE_Processing
 * http:
 * 
 * Copyright (c) 2013 Joshua Davis & James Cruz
 * 
 * Distributed under the BSD License. See LICENSE.txt for details.
 * 
 * All rights reserved.
 */
public static class HProximity extends HBehavior {
	private HDrawable _target, _neighbor;
	private int _property;
	private float _min, _max, _range, _radius, _rSq, _dist, _spring, _ease, _goal, _speed, _value;
	private float _origw, _origh, _origd;
	private boolean _useMouse;
	public HProximity () {
		_property = HConstants.ALPHA;
		_radius = 100;
		_rSq = _radius * _radius;
		_spring = 0.9f;
		_ease = 0.3f;
		_min = 50;
		_max = 255;
		_range = (_max - _min);
		_value = _min;
		_speed = 0;
		_goal = _max;
		_useMouse = true;
		_neighbor = null;
		register();
	}
	public HProximity (float spring, float ease, float min, float max, float radius) {
		_property = HConstants.ALPHA;
		_radius = radius;
		_rSq = _radius * _radius;
		_spring = spring;
		_ease = ease;
		_min = min;
		_max = max;
		_range = (_max - _min);
		_value = _min;
		_speed = 0;
		_goal = 0;
		_useMouse = true;
		_neighbor = null;
		register();
	}
	public HProximity target(HDrawable d) {
		_target = d;
		if(d != null) {
			_origw = d.width();
			_origh = d.height();
			_origd = 0;
		}
		return this;
	}
	public HProximity target(HDrawable3D d) {
		_target = d;
		if(d != null) {
			_origw = d.width();
			_origh = d.height();
			_origd = d.depth();
		}
		return this;
	}
	public HDrawable target() {
		return _target;
	}
	public HProximity property(int id) {
		_property = id;
		return this;
	}
	public int property() {
		return _property;
	}
	public HProximity neighbor(HDrawable d) {
		_useMouse = false;
		_neighbor = d;
		return this;	
	}
	public HDrawable neighbor() {
		return _neighbor;
	}
	public HProximity min(float f) {
		_min = f;
		_value = f;
		_range = (_max - _min);
		return this;
	}
	public float min() {
		return _min;
	}
	public HProximity max(float f) {
		_max = f;
		_goal = f;
		_range = (_max - _min);
		return this;
	}
	public float max() {
		return _max;
	}
	public HProximity radius(float f) {
		_radius = f;
		_rSq = _radius * _radius;
		return this;
	}
	public float radius() {
		return _radius;
	}
	public HProximity spring(float f) {
		_spring = f;
		return this;
	}
	public float spring() {
		return _spring;
	}
	public HProximity ease(float f) {
		_ease = f;
		return this;
	}
	public float ease() {
		return _ease;
	}
	private void _run(float x, float y) {
		_dist = ((_target.x() - x) * (_target.x() - x)) + ((_target.y() - y) * (_target.y() - y));
		if (_dist < _rSq) {
			_goal = (1 - (_dist / _rSq)) * _range + _min;
		} else {
			_goal = _min;
		}
		_speed = (_speed * _spring) + ((_goal - _value) * _ease);
		_value += _speed;
	}
	public void runBehavior(PApplet app) {
		if(_target==null) return;
		if (_useMouse == true) {
			_run(app.mouseX, app.mouseY);
		} else {
			_run(_neighbor.x(), _neighbor.y());
		}
		float v1 = _value;
		float v2 = _value;
		float v3 = _value;
		switch(_property) {
			case HConstants.WIDTH:		_target.width(_value); break;
			case HConstants.HEIGHT:		_target.height(_value); break;
			case HConstants.SCALE:
				v1 *= _origw;
				v2 *= _origh;
				v3 *= _origd;
			case HConstants.SIZE: 		_target.size(new PVector(v1, v2, v3)); break;
			case HConstants.ALPHA:		_target.alpha(Math.round(_value)); break;
			case HConstants.ROTATIONX:	_target.rotationX(_value); break;
			case HConstants.ROTATIONY:	_target.rotationY(_value); break;
			case HConstants.ROTATIONZ:	_target.rotationZ(_value); break;
			case HConstants.DROTATIONX:	_target.rotateX(_value); break;
			case HConstants.DROTATIONY:	_target.rotateY(_value); break;
			case HConstants.DROTATIONZ:	_target.rotateZ(_value); break;
			default: break;
		}
	}
	public HProximity register() {
		return (HProximity) super.register();
	}
	public HProximity unregister() {
		return (HProximity) super.unregister();
	}
}
/*
 * HYPE_Processing
 * http:
 * 
 * Copyright (c) 2013 Joshua Davis & James Cruz
 * 
 * Distributed under the BSD License. See LICENSE.txt for details.
 * 
 * All rights reserved.
 */
public static class HRandomTrigger extends HTrigger {
	private float _chance;
	public HRandomTrigger() {}
	public HRandomTrigger(float percChance) {
		_chance = percChance;
	}
	public HRandomTrigger chance(float chancePercentage) {
		_chance = chancePercentage;
		return this;
	}
	public float chance() {
		return _chance;
	}
	public void runBehavior(PApplet app) {
		if(app.random(1) <= _chance) _callback.run(null);
	}
	public HRandomTrigger callback(HCallback cb) {
		return (HRandomTrigger) super.callback(cb);
	}
}
/*
 * HYPE_Processing
 * http:
 * 
 * Copyright (c) 2013 Joshua Davis & James Cruz
 * 
 * Distributed under the BSD License. See LICENSE.txt for details.
 * 
 * All rights reserved.
 */
public static class HRect extends HDrawable {
	private float _tl, _tr, _bl, _br;
	public HRect() {}
	public HRect(float s) {
		size(s);
	}
	public HRect(float w, float h) {
		size(w,h);
	}
	public HRect(float w, float h, float roundingRadius) {
		size(w,h);
		rounding(roundingRadius);
	}
	public HRect createCopy() {
		HRect copy = new HRect();
		copy._tl = _tl;
		copy._tr = _tr;
		copy._bl = _bl;
		copy._br = _br;
		copy.copyPropertiesFrom(this);
		return copy;
	}
	public HRect rounding(float radius) {
		_tl = _tr = _bl = _br = radius;
		return this;
	}
	public HRect rounding(
		float topleft, float topright,
		float bottomright, float bottomleft
	) {
		_tl = topleft;
		_tr = topright;
		_br = bottomright;
		_bl = bottomleft;
		return this;
	}
	public float rounding() {
		return roundingTL();
	}
	public HRect roundingTL(float radius) {
		_tl = radius;
		return this;
	}
	public float roundingTL() {
		return _tl;
	}
	public HRect roundingTR(float radius) {
		_tr = radius;
		return this;
	}
	public float roundingTR() {
		return _tr;
	}
	public HRect roundingBR(float radius) {
		_br = radius;
		return this;
	}
	public float roundingBR() {
		return _br;
	}
	public HRect roundingBL(float radius) {
		_bl = radius;
		return this;
	}
	public float roundingBL() {
		return _bl;
	}
	public void draw( PGraphics g, boolean usesZ,
		float drawX, float drawY, float alphaPc
	) {
		applyStyle(g,alphaPc);
		g.rect(drawX,drawY, _width,_height, _tl,_tr,_br,_bl);
	}
}
/*
 * HYPE_Processing
 * http:
 * 
 * Copyright (c) 2013 Joshua Davis & James Cruz
 * 
 * Distributed under the BSD License. See LICENSE.txt for details.
 * 
 * All rights reserved.
 */
public static interface HRotatable {
	public float rotationRad();
	public HRotatable rotationRad(float rad);
}
/*
 * HYPE_Processing
 * http:
 * 
 * Copyright (c) 2013 Joshua Davis & James Cruz
 * 
 * Distributed under the BSD License. See LICENSE.txt for details.
 * 
 * All rights reserved.
 */
public static class HRotate extends HBehavior {
	private HRotatable _target;
	private float _speedRad;
	public HRotate() {}
	public HRotate(HRotatable newTarget, float dDeg) {
		target(newTarget);
		_speedRad = dDeg * HConstants.D2R;
	}
	public HRotate target(HRotatable r) {
		if(r == null) unregister();
		else register();
		_target = r;
		return this;
	}
	public HRotatable target() {
		return _target;
	}
	public HRotate speed(float dDeg) {
		_speedRad = dDeg * HConstants.D2R;
		return this;
	}
	public float speed() {
		return _speedRad * HConstants.R2D;
	}
	public HRotate speedRad(float dRad) {
		_speedRad = dRad;
		return this;
	}
	public float speedRad() {
		return _speedRad;
	}
	public void runBehavior(PApplet app) {
		float rot = _target.rotationRad() + _speedRad;
		_target.rotationRad(rot);
	}
	public HRotate register() {
		return (HRotate) super.register();
	}
	public HRotate unregister() {
		return (HRotate) super.unregister();
	}
}
/*
 * HYPE_Processing
 * http:
 * 
 * Copyright (c) 2013 Joshua Davis & James Cruz
 * 
 * Distributed under the BSD License. See LICENSE.txt for details.
 * 
 * All rights reserved.
 */
public static class HShape extends HDrawable {
	private PShape _shape;
	private int[] _randomFills, _randomStrokes;
	public HShape() {
		shape(null);
	}
	public HShape(Object shapeArg) {
		shape(shapeArg);
	}
	public HShape createCopy() {
		HShape copy = new HShape(_shape);
		copy.copyPropertiesFrom(this);
		return copy;
	}
	public HShape resetSize() {
		if(_shape == null) {
			size(0,0);
		} else {
			size(_shape.width,_shape.height);
		}
		return this;
	}
	public HShape shape(Object shapeArg) {
		if(shapeArg instanceof PShape) {
			_shape = (PShape) shapeArg;
		} else if(shapeArg instanceof String) {
			_shape = H.app().loadShape((String) shapeArg);
		} else if(shapeArg instanceof HShape) {
			_shape = ((HShape) shapeArg)._shape;
		} else if(shapeArg == null) {
			_shape = null;
		}
		return resetSize();
	}
	public PShape shape() {
		return _shape;
	}
	public HShape enableStyle(boolean b) {
		if(b) _shape.enableStyle();
		else _shape.disableStyle();
		return this;
	}
	public HShape randomColors(HColorPool colors) {
		int numChildren = _shape.getChildCount();
		boolean isFill = colors.appliesFill();
		boolean isStroke = colors.appliesStroke();
		if(isFill) {
			if(_randomFills==null || _randomFills.length<numChildren)
				_randomFills = new int[numChildren];
		} else {
			_randomFills = null;
		}
		if(isStroke) {
			if(_randomStrokes==null || _randomStrokes.length<numChildren)
				_randomStrokes = new int[numChildren];
		} else {
			_randomStrokes = null;
		}
		for(int i=0; i<numChildren; ++i) {
			if(isFill) _randomFills[i] = colors.getColor();
			if(isStroke) _randomStrokes[i] = colors.getColor();
		}
		_shape.disableStyle();
		return this;
	}
	public HShape resetRandomColors() {
		_shape.enableStyle();
		_randomFills = null;
		_randomStrokes = null;
		return this;
	}
	public void draw( PGraphics g, boolean usesZ,
		float drawX,float drawY,float alphaPc
	) {
		if(_shape == null) return;
		int wscale = 1;
		int hscale = 1;
		float w = _width;
		float h = _height;
		if(_width < 0) {
			w = -_width;
			wscale = -1;
			drawX = -drawX;
		}
		if(_height < 0) {
			h = -_height;
			hscale = -1;
			drawY = - drawY;
		}
		applyStyle(g,alphaPc);
		g.pushMatrix();
		g.scale(wscale, hscale);
		if(_randomFills==null && _randomStrokes==null) {
			g.shape(_shape, drawX,drawY, w,h);
		} else for(int i=0; i<_shape.getChildCount(); ++i) {
			PShape childShape = _shape.getChild(i);
			childShape.width = _shape.width;
			childShape.height = _shape.height;
			if(_randomFills != null) g.fill(_randomFills[i]);
			if(_randomStrokes != null) g.stroke(_randomStrokes[i]);
			g.shape(childShape, drawX,drawY, w,h);
		}
		g.popMatrix();
	}
}
/*
 * HYPE_Processing
 * http:
 * 
 * Copyright (c) 2013 Joshua Davis & James Cruz
 * 
 * Distributed under the BSD License. See LICENSE.txt for details.
 * 
 * All rights reserved.
 */
public static class HShapeLayout implements HLayout {
	private HDrawable _target;
	private float[] _bounds;
	private int _iterationLimit;
	public HShapeLayout() {
		_iterationLimit = 1024;
		_bounds = new float[4];
	}
	public HShapeLayout iterationLimit(int i) {
		_iterationLimit = i;
		return this;
	}
	public int iterationLimit() {
		return _iterationLimit;
	}
	public HShapeLayout target(HDrawable d) {
		_target = d;
		if(_target != null) _target.bounds(_bounds);
		return this;
	}
	public HDrawable target() {
		return _target;
	}
	public void applyTo(HDrawable target) {
		PVector pt = getNextPoint();
		if(pt != null) target.loc(pt);
	}
	public PVector getNextPoint() {
		if(_target == null) return null;
		float x1 = _bounds[0];
		float y1 = _bounds[1];
		float x2 = _bounds[0] + _bounds[2];
		float y2 = _bounds[1] + _bounds[3];
		for(int i=0; i<_iterationLimit; ++i) {
			float x = H.app().random(x1,x2);
			float y = H.app().random(y1,y2);
			if(_target.contains(x,y))
				return new PVector(x,y);
		}
		return null;
	}
}
/*
 * HYPE_Processing
 * http:
 * 
 * Copyright (c) 2013 Joshua Davis & James Cruz
 * 
 * Distributed under the BSD License. See LICENSE.txt for details.
 * 
 * All rights reserved.
 */
public static class HSphere extends HDrawable3D {
	public HSphere() {}
	public HSphere(float radius) {
		radius(radius);
	}
	public HSphere(float radiusw, float radiush, float radiusd) {
		radius(radiusw, radiush, radiusd);
	}
	public HSphere radius(float f) {
		return (HSphere) size(f*2);
	}
	public HSphere radius(float radiusw, float radiush, float radiusd) {
		return (HSphere) size(radiusw*2, radiush*2, radiusd*2);
	}
	protected void onResize(float oldW, float oldH, float newW, float newH) {
		_height = _depth = _width;
		super.onResize(oldW, oldH, newW, newH);
	}
	public HSphere createCopy() {
		HSphere copy = new HSphere();
		copy.copyPropertiesFrom(this);
		copy._depth = _depth;
		copy._anchorW = _anchorW;
		return copy;
	}
	public void draw( PGraphics g, boolean usesZ,
		float drawX, float drawY, float currAlphaPc
	) {
		applyStyle(g, currAlphaPc);
		g.pushMatrix();
			g.translate(drawX+_width/2, drawY+_height/2, -anchorZ()+_depth/2);
			/*
				Hot fix for weird stroke on sphere when scaling
				TODO: need to look at this again to make it better
			*/
			g.sphere(_width);
		g.popMatrix();
	}
}
/*
 * HYPE_Processing
 * http:
 * 
 * Copyright (c) 2013 Joshua Davis & James Cruz
 * 
 * Distributed under the BSD License. See LICENSE.txt for details.
 * 
 * All rights reserved.
 */
public static class HSprite extends HDrawable {
	private PImage _texture;
	private int _tX, _tY;
	public HSprite() {}
	public HSprite(float s) {
		size(s);
	}
	public HSprite(float w, float h) {
		size(w,h);
	}
	public HSprite texture(Object imgArg) {
		_texture = H.getImage(imgArg);
		if(_texture == null) {
			_tX = 0;
			_tY = 0;
		} else {
			_tX = _texture.width;
			_tY = _texture.height;
		}
		return this;
	}
	public HSprite createCopy() {
		HSprite copy = new HSprite();
		copy._texture = _texture;
		copy._tX = _tX;
		copy._tY = _tY;
		copy._extras = _extras;
		copy.copyPropertiesFrom(this);
		return copy;
	}
	public void draw( PGraphics g, boolean usesZ,
		float drawX, float drawY, float alphaPc
	) {
		g.textureMode(NORMAL);
		g.noStroke();
		g.tint(_fill);
	    g.pushMatrix();
	    	g.translate(drawX, drawY);
	    	g.scale(_width, _height);
	    	g.beginShape();
		    	g.texture(_texture);
				g.vertex(0, 0, 0, 0);
				g.vertex(1, 0, 1, 0);
				g.vertex(1, 1, 1, 1);
				g.vertex(0, 1, 0, 1);
			g.endShape();
		g.popMatrix();
	}
}
/*
 * HYPE_Processing
 * http:
 * 
 * Copyright (c) 2013 Joshua Davis & James Cruz
 * 
 * Distributed under the BSD License. See LICENSE.txt for details.
 * 
 * All rights reserved.
 */
public static class HStage extends HDrawable implements HImageHolder {
	private PApplet _app;
	private PImage _bgImg;
	private boolean _autoClears;
	private boolean _showsFPS;
	public HStage(PApplet papplet) {
		_app = papplet;
		_autoClears = true;
		background(HConstants.DEFAULT_BACKGROUND_COLOR);
	}
	public boolean invalidChild(HDrawable destParent) {
		return true;
	}
	public HStage background(int clr) {
		_fill = clr;
		return clear();
	}
	public HStage backgroundImg(Object arg) {
		return image(arg);
	}
	public HStage image(Object imgArg) {
		_bgImg = H.getImage(imgArg);
		return clear();
	}
	public PImage image() {
		return _bgImg;
	}
	/** @deprecated The method autoClears(boolean) should be used instead */
	public HStage autoClear(boolean b) {
		_autoClears = b;
		return this;
	}
	public HStage autoClears(boolean b) {
		_autoClears = b;
		return this;
	}
	public boolean autoClears() {
		return _autoClears;
	}
	public HStage clear() {
		if(_bgImg == null) _app.background(_fill);
		else _app.background(_bgImg);
		return this;
	}
	public HDrawable fill(int clr) {
		background(clr);
		return this;
	}
	public HDrawable fill(int clr, int alpha) {
		return fill(clr);
	}
	public HDrawable fill(int r, int g, int b) {
		return fill(HColors.merge(255,r,g,b));
	}
	public HDrawable fill(int r, int g, int b, int a) {
		return fill(r,g,b);
	}
	public PVector size() {
		return new PVector(_app.width,_app.height);
	}
	public float width() {
		return _app.width;
	}
	public float height() {
		return _app.height;
	}
	public HStage showsFPS(boolean b) {
		_showsFPS = b;
		return this;
	}
	public boolean showsFPS() {
		return _showsFPS;
	}
	public void paintAll(PGraphics g, boolean usesZ, float currAlphaPc) {
		g.pushStyle();
			if(_autoClears) clear();
			HDrawable child = _firstChild;
			while(child != null) {
				child.paintAll(g, usesZ, currAlphaPc);
				child = child.next();
			}
		g.popStyle();
		if(_showsFPS) {
			g.pushStyle();
				g.fill(H.BLACK);
				g.text(_app.frameRate,1,17);
				g.fill(H.WHITE);
				g.text(_app.frameRate,0,16);
			g.popStyle();
		}
	}
	public void draw(PGraphics g,boolean b,float x,float y,float p) {}
	public HDrawable createCopy() { return null; }
}
/*
 * HYPE_Processing
 * http:
 * 
 * Copyright (c) 2013 Joshua Davis & James Cruz
 * 
 * Distributed under the BSD License. See LICENSE.txt for details.
 * 
 * All rights reserved.
 */
public static class HSwarm extends HBehavior {
	private HLinkedHashSet<HLocatable> _goals;
	private HLinkedHashSet<HDirectable> _targets;
	private float _speed, _turnEase, _twitchRad, _idleGoalX, _idleGoalY;
	public HSwarm() {
		_speed = 1;
		_turnEase = 1;
		_twitchRad = 0;
		_goals = new HLinkedHashSet<HLocatable>();
		_targets = new HLinkedHashSet<HDirectable>();
	}
	public HSwarm addTarget(HDirectable t) {
		if(_targets.size() <= 0) register();
		_targets.add(t);
		return this;
	}
	public HSwarm removeTarget(HDirectable t) {
		_targets.remove(t);
		if(_targets.size() <= 0) unregister();
		return this;
	}
	public HLinkedHashSet<HDirectable> targets() {
		return _targets;
	}
	public HSwarm addGoal(HLocatable g) {
		_goals.add(g);
		return this;
	}
	public HSwarm addGoal(float x, float y) {
		return addGoal(new HVector(x,y));
	}
	public HSwarm addGoal(float x, float y, float z) {
		return addGoal(new HVector(x,y,z));
	}
	public HSwarm removeGoal(HLocatable g) {
		_goals.remove(g);
		return this;
	}
	public HLinkedHashSet<HLocatable> goals() {
		return _goals;
	}
	public HSwarm idleGoal(float x, float y) {
		_idleGoalX = x;
		_idleGoalY = y;
		return this;
	}
	public float idleGoalX() {
		return _idleGoalX;
	}
	public float idleGoalY() {
		return _idleGoalY;
	}
	public HSwarm speed(float s) {
		_speed = s;
		return this;
	}
	public float speed() {
		return _speed;
	}
	public HSwarm turnEase(float e) {
		_turnEase = e;
		return this;
	}
	public float turnEase() {
		return _turnEase;
	}
	public HSwarm twitch(float deg) {
		_twitchRad = deg * HConstants.D2R;
		return this;
	}
	public HSwarm twitchRad(float rad) {
		_twitchRad = rad;
		return this;
	}
	public float twitch() {
		return _twitchRad * HConstants.R2D;
	}
	public float twitchRad() {
		return _twitchRad;
	}
	private HLocatable getGoal(HDirectable target, PApplet app) {
		HLocatable goal = null;
		float nearestDist = -1;
		for(HLocatable h : _goals) {
			float dist = HMath.dist(target.x(),target.y(), h.x(),h.y());
			if(nearestDist<0 || dist<nearestDist) {
				nearestDist = dist;
				goal = h;
			}
		}
		return goal;
	}
	public void runBehavior(PApplet app) {
		int numTargets = _targets.size();
		Iterator<HDirectable> it = _targets.iterator();
		for(int i=0; i<numTargets; ++i) {
			HDirectable target = it.next();
			float rot = target.rotationRad();
			float tx = target.x();
			float ty = target.y();
			float goalx = _idleGoalX;
			float goaly = _idleGoalY;
			float goalz = 0;
			HLocatable goal = getGoal(target, app);
			if(goal != null) {
				goalx = goal.x();
				goaly = goal.y();
				goalz = goal.z();
			}
			float tmp = HMath.xAxisAngle(tx,ty, goalx,goaly) - rot;
			float dRot = _turnEase * (float)
				Math.atan2( Math.sin(tmp), Math.cos(tmp) );
			rot += dRot;
			float noise = app.noise(i*numTargets + app.frameCount/8f);
			rot += HMath.map(noise, 0,1, -_twitchRad,_twitchRad);
			target.rotationRad(rot);
			target.x(target.x() + (float)Math.cos(rot)*_speed);
			target.y(target.y() + (float)Math.sin(rot)*_speed);
			target.z(goalz);
		}
	}
	public HSwarm register() {
		return (HSwarm) super.register();
	}
	public HSwarm unregister() {
		return (HSwarm) super.unregister();
	}
}
/*
 * HYPE_Processing
 * http:
 * 
 * Copyright (c) 2013 Joshua Davis & James Cruz
 * 
 * Distributed under the BSD License. See LICENSE.txt for details.
 * 
 * All rights reserved.
 */
public static class HText extends HDrawable {
	private PFont _font;
	private String _text;
	private float _descent;
	public HText() {
		this(null,16);
	}
	public HText(String textString) {
		this(textString,16,null);
	}
	public HText(String textString, float size) {
		this(textString,size,null);
	}
	public HText(String textString, float size, Object fontArg) {
		_text = textString;
		_height = size;
		font(fontArg);
		height(size);
		_fill = HConstants.BLACK;
		_stroke = HConstants.CLEAR;
	}
	public HText createCopy() {
		HText copy = new HText(_text,_height,_font);
		copy.copyPropertiesFrom(this);
		copy.adjustMetrics();
		return copy;
	}
	public HText text(String txt) {
		_text = txt;
		adjustMetrics();
		return this;
	}
	public String text() {
		return _text;
	}
	public HText font(Object arg) {
		PApplet app = H.app();
		if(arg instanceof PFont) {
			_font = (PFont) arg;
		} else if(arg instanceof String) {
			String str = (String) arg;
			_font = (str.indexOf(".vlw",str.length()-4) > 0)?
				app.loadFont(str) : app.createFont(str,64);
		} else if(arg instanceof HText) {
			_font = ((HText) arg)._font;
		} else if(arg == null) {
			_font = app.createFont("SansSerif",64);
		}
		adjustMetrics();
		return this;
	}
	public PFont font() {
		return _font;
	}
	public HText fontSize(float f) {
		return height(f);
	}
	public float fontSize() {
		return _height;
	}
	private void adjustMetrics() {
		PApplet app = H.app();
		app.pushStyle();
		app.textFont(_font,(_height < 0)? -_height : _height);
		_descent = app.textDescent();
		_width = (_text==null)? 0 :
			(_width<0)? -app.textWidth(_text) : app.textWidth(_text);
		app.popStyle();
	}
	public HText width(float w) {
		if(w<0 == _width>0) _width = -_width;
		return this;
	}
	public HText height(float h) {
		_height = h;
		adjustMetrics();
		return this;
	}
	public boolean containsRel(float relX, float relY) {
		if(_text == null || _height == 0) return false;
		int numChars = _text.length();
		float ratio = 64 / _height;
		float xoff = 0;
		float yoff = (_height - _descent) * ratio;
		relX *= ratio;
		relY *= ratio;
		for(int i=0; i<numChars; ++i) {
			char c = _text.charAt(i);
			PFont.Glyph g = _font.getGlyph(c);
			int pxx = Math.round(relX - xoff);
			int pxy = Math.round(relY - yoff) + g.topExtent;
			if(g.image.get(pxx, pxy)>>>24 > 0) return true;
			xoff += g.setWidth;
		}
		return false;
	} 
	public void draw( PGraphics g, boolean usesZ,
		float drawX, float drawY, float alphaPc
	) {
		if(_text == null) return;
		applyStyle(g,alphaPc);
		int wscale = 1;
		int hscale = 1;
		float h = _height;
		if(_width < 0) {
			wscale = -1;
			drawX = -drawX;
		}
		if(_height < 0) {
			h = -_height;
			hscale = -1;
			drawY = -drawY;
		}
		g.pushMatrix();
			g.scale(wscale, hscale);
			g.textFont(_font,h);
			g.text(_text,drawX,drawY+h-_descent);
		g.popMatrix();
	}
}
/*
 * HYPE_Processing
 * http:
 * 
 * Copyright (c) 2013 Joshua Davis & James Cruz
 * 
 * Distributed under the BSD License. See LICENSE.txt for details.
 * 
 * All rights reserved.
 */
public static class HTimer extends HTrigger {
	private int _lastInterval, _interval, _cycleCounter, _numCycles;
	private boolean _usesFrames;
	public HTimer() {
		_interval = 1000;
		_lastInterval = -1;
	}
	public HTimer(int timerInterval) {
		_interval = timerInterval;
	}
	public HTimer(int timerInterval, int numberOfCycles) {
		_interval = timerInterval;
		_numCycles = numberOfCycles;
	}
	public HTimer interval(int i) {
		_interval = i;
		return this;
	}
	public int interval() {
		return _interval;
	}
	public HTimer cycleCounter(int cycleIndex) {
		_cycleCounter = cycleIndex;
		return this;
	}
	public int cycleCounter() {
		return _cycleCounter;
	}
	public HTimer numCycles(int cycles) {
		_numCycles = cycles;
		return this;
	}
	public int numCycles() {
		return _numCycles;
	}
	public HTimer cycleIndefinitely() {
		_numCycles = 0;
		return this;
	}
	public HTimer useMillis() {
		_usesFrames = false;
		return this;
	}
	public boolean usesMillis() {
		return !_usesFrames;
	}
	public HTimer useFrames() {
		_usesFrames = true;
		return this;
	}
	public boolean usesFrames() {
		return _usesFrames;
	}
	public void runBehavior(PApplet app) {
		int curr = (_usesFrames)? app.frameCount : app.millis();
		if(_lastInterval < 0) _lastInterval = curr;
		if(curr-_lastInterval >= _interval) {
			_lastInterval = curr;
			_callback.run(_cycleCounter);
			if(_numCycles > 0 && ++_cycleCounter >= _numCycles) unregister();
		}
	}
	public HTimer callback(HCallback cb) {
		return (HTimer) super.callback(cb);
	}
	public HTimer register() {
		return (HTimer) super.register();
	}
	public HTimer unregister() {
		_numCycles = 0;
		_lastInterval = -1;
		return (HTimer) super.unregister();
	}
}
/*
 * HYPE_Processing
 * http:
 * 
 * Copyright (c) 2013 Joshua Davis & James Cruz
 * 
 * Distributed under the BSD License. See LICENSE.txt for details.
 * 
 * All rights reserved.
 */
public static abstract class HTrigger extends HBehavior {
	protected HCallback _callback;
	public HTrigger() {
		register();
		_callback = HConstants.NOP;
	}
	public HTrigger callback(HCallback cb) {
		_callback = (cb==null)? HConstants.NOP : cb;
		return this;
	}
	public HCallback callback() {
		return _callback;
	}
}
/*
 * HYPE_Processing
 * http:
 * 
 * Copyright (c) 2013 Joshua Davis & James Cruz
 * 
 * Distributed under the BSD License. See LICENSE.txt for details.
 * 
 * All rights reserved.
 */
public static class HTween extends HBehavior {
	private HDrawable _target;
	private HCallback _callback;
	private float _s1, _s2, _s3;
	private float _e1, _e2, _e3;
	private float _curr1, _curr2, _curr3;
	private float _origw, _origh;
	private float _raw, _dRaw, _spring, _ease;
	private int _property;
	public HTween() {
		_ease = 1;
		_callback = HConstants.NOP;
		register();
	}
	public HTween target(HDrawable d) {
		_target = d;
		if(d != null) {
			_origw = d.width();
			_origh = d.height();
		}
		return this;
	}
	public HDrawable target() {
		return _target;
	}
	public HTween callback(HCallback c) {
		_callback = (c==null)? HConstants.NOP : c;
		return this;
	}
	public HCallback callback() {
		return _callback;
	}
	public HTween start(float a) {
		return start(a,a);
	}
	public HTween start(float a, float b) {
		return start(a,b,0);
	}
	public HTween start(float a, float b, float c) {
		_s1 = a;
		_s2 = b;
		_s3 = c;
		return this;
	}
	public float start() {
		return _s1;
	}
	public float start1() {
		return _s1;
	}
	public float start2() {
		return _s2;
	}
	public float start3() {
		return _s3;
	}
	public HTween end(float a) {
		return end(a,a);
	}
	public HTween end(float a, float b) {
		return end(a,b,0);
	}
	public HTween end(float a, float b, float c) {
		_e1 = a;
		_e2 = b;
		_e3 = c;
		return this;
	}
	public float end() {
		return _e1;
	}
	public float end1() {
		return _e1;
	}
	public float end2() {
		return _e2;
	}
	public float end3() {
		return _e3;
	}
	public HTween spring(float f) {
		_spring = f;
		return this;
	}
	public float spring() {
		return _spring;
	}
	public HTween ease(float f) {
		_ease = f;
		return this;
	}
	public float ease() {
		return _ease;
	}
	public HTween property(int id) {
		_property = id;
		return this;
	}
	public int property() {
		return _property;
	}
	public float nextRaw() {
		_raw += (_dRaw) = (_dRaw*_spring + (1-_raw)*_ease);
		float c = HMath.round512(_raw);
		_curr1 = HMath.map(c,0,1,_s1,_e1);
		_curr2 = HMath.map(c,0,1,_s2,_e2);
		_curr3 = HMath.map(c,0,1,_s3,_e3);
		return c;
	}
	public float curr() {
		return _curr1;
	}
	public float curr1() {
		return _curr1;
	}
	public float curr2() {
		return _curr2;
	}
	public float curr3() {
		return _curr3;
	}
	public void runBehavior(PApplet app) {
		if(_target==null) return;
		float c = nextRaw();
		float v1 = _curr1;
		float v2 = _curr2;
		float v3 = _curr3;
		switch(_property) {
		case HConstants.WIDTH:		_target.width(v1); break;
		case HConstants.HEIGHT:		_target.height(v1); break;
		case HConstants.SCALE:
			v1 *= _origw;
			v2 *= _origh;
		case HConstants.SIZE:		_target.size(v1,v2); break;
		case HConstants.ALPHA:		_target.alpha(Math.round(v1)); break;
		case HConstants.X:			_target.x(v1); break;
		case HConstants.Y:			_target.y(v1); break;
		case HConstants.Z:			_target.z(v1); break;
		case HConstants.LOCATION:	_target.loc(v1,v2,v3); break;
		case HConstants.ROTATIONX:	_target.rotationX(v1); break;
		case HConstants.ROTATIONY:	_target.rotationY(v1); break;
		case HConstants.ROTATIONZ:	_target.rotationZ(v1); break;
		case HConstants.DROTATIONX:	_target.rotateX(v1); break;
		case HConstants.DROTATIONY:	_target.rotateY(v1); break;
		case HConstants.DROTATIONZ:	_target.rotateZ(v1); break;
		case HConstants.DX:			_target.move(v1,0); break;
		case HConstants.DY:			_target.move(0,v1); break;
		case HConstants.DLOC:		_target.move(v1,v1); break;
		default: break;
		}
		if(c==1 && HMath.round512(_dRaw)==0) {
			unregister();
			_callback.run(this);
		}
	}
	public HTween register() {
		return (HTween) super.register();
	}
	public HTween unregister() {
		_raw = _dRaw = 0;
		return (HTween) super.unregister();
	}
}
/*
 * HYPE_Processing
 * http:
 * 
 * Copyright (c) 2013 Joshua Davis & James Cruz
 * 
 * Distributed under the BSD License. See LICENSE.txt for details.
 * 
 * All rights reserved.
 */
public static class HVector implements HLocatable {
	private float _x, _y, _z;
	public HVector() {}
	public HVector(float xCoord, float yCoord) {
		_x = xCoord;
		_y = yCoord;
	}
	public HVector(float xCoord, float yCoord, float zCoord) {
		_x = xCoord;
		_y = yCoord;
		_z = zCoord;
	}
	public float x() {
		return _x;
	}
	public HVector x(float newX) {
		_x = newX;
		return this;
	}
	public float y() {
		return _y;
	}
	public HVector y(float newY) {
		_y = newY;
		return this;
	}
	public float z() {
		return _z;
	}
	public HVector z(float newZ) {
		_z = newZ;
		return this;
	}
}
/*
 * HYPE_Processing
 * http:
 * 
 * Copyright (c) 2013 Joshua Davis & James Cruz
 * 
 * Distributed under the BSD License. See LICENSE.txt for details.
 * 
 * All rights reserved.
 */
public static class HVelocity extends HBehavior {
	private float _velocityX, _velocityY, _accelX, _accelY;
	private HLocatable _target;
	public HVelocity target(HLocatable t) {
		if(t == null) unregister();
		else register();
		_target = t;
		return this;
	}
	public HLocatable target() {
		return _target;
	}
	public HVelocity velocity(float velocity, float deg) {
		return velocityRad(velocity, deg*HConstants.D2R);
	}
	public HVelocity velocityRad(float velocity, float rad) {
		_velocityX = velocity * (float)Math.cos(rad);
		_velocityY = velocity * (float)Math.sin(rad);
		return this;
	}
	public HVelocity velocityX(float dx) {
		_velocityX = dx;
		return this;
	}
	public float velocityX() {
		return _velocityX;
	}
	public HVelocity velocityY(float dy) {
		_velocityY = dy;
		return this;
	}
	public float velocityY() {
		return _velocityY;
	}
	public HVelocity launchTo(float goalX, float goalY, int numFrames) {
		if(_target == null) {
			HWarnings.warn("Null Target", "HVelocity.launchTo()",
					HWarnings.NULL_TARGET);
		} else {
			float nfsq = numFrames*numFrames;
			_velocityX = (goalX - _target.x() - _accelX*nfsq/2) / numFrames;
			_velocityY = (goalY - _target.y() - _accelY*nfsq/2) / numFrames;
		}
		return this;
	}
	public HVelocity accel(float acceleration, float deg) {
		return accelRad(acceleration, deg*HConstants.D2R);
	}
	public HVelocity accelRad(float acceleration, float rad) {
		_accelX = acceleration * (float)Math.cos(rad);
		_accelY = acceleration * (float)Math.sin(rad);
		return this;
	}
	public HVelocity accelX(float ddx) {
		_accelX = ddx;
		return this;
	}
	public float accelX() {
		return _accelX;
	}
	public HVelocity accelY(float ddy) {
		_accelY = ddy;
		return this;
	}
	public float accelY() {
		return _accelY;
	}
	public void runBehavior(PApplet app) {
		_target.x(_target.x() + _velocityX);
		_target.y(_target.y() + _velocityY);
		_velocityX += _accelX;
		_velocityY += _accelY;
	}
	public HVelocity register() {
		return (HVelocity) super.register();
	}
	public HVelocity unregister() {
		return (HVelocity) super.unregister();
	}
}
/*
 * HYPE_Processing
 * http:
 * 
 * Copyright (c) 2013 Joshua Davis & James Cruz
 * 
 * Distributed under the BSD License. See LICENSE.txt for details.
 * 
 * All rights reserved.
 */
public static class HVertex implements HLocatable {
	public static final float LINE_TOLERANCE = 1.5f;
	private HPath _path;
	private byte _numControlPts;
	private float _u, _v, _cu1, _cv1, _cu2, _cv2;
	public HVertex(HPath parentPath) {
		_path = parentPath;
	}
	public HVertex createCopy(HPath newParentPath) {
		HVertex copy = new HVertex(newParentPath);
		copy._numControlPts = _numControlPts;
		copy._u = _u;
		copy._v = _v;
		copy._cu1 = _cu1;
		copy._cv1 = _cv1;
		copy._cu2 = _cu2;
		copy._cv2 = _cv2;
		return copy;
	}
	public HPath path() {
		return _path;
	}
	public HVertex numControlPts(byte b) {
		_numControlPts = b;
		return this;
	}
	public byte numControlPts() {
		return _numControlPts;
	}
	public boolean isLine() {
		return (_numControlPts <= 0);
	}
	public boolean isCurved() {
		return (_numControlPts > 0);
	}
	public boolean isQuadratic() {
		return (_numControlPts == 1);
	}
	public boolean isCubic() {
		return (_numControlPts >= 2);
	}
	public HVertex set(float x, float y) {
		return setUV(
			_path.x2u(x),   _path.y2v(y));
	}
	public HVertex set(float cx, float cy, float x, float y) {
		return setUV(
			_path.x2u(cx), _path.y2v(cy),
			_path.x2u(x),  _path.y2v(y));
	}
	public HVertex set(
		float cx1, float cy1,
		float cx2, float cy2,
		float x, float y
	) {
		return setUV(
			_path.x2u(cx1), _path.y2v(cy1),
			_path.x2u(cx2), _path.y2v(cy2),
			_path.x2u(x),   _path.y2v(y));
	}
	public HVertex setUV(float u, float v) {
		_numControlPts = 0;
		_u = u;
		_v = v;
		return this;
	}
	public HVertex setUV(float cu, float cv, float u, float v) {
		_numControlPts = 1;
		_u = u;
		_v = v;
		_cu1 = cu;
		_cv1 = cv;
		return this;
	}
	public HVertex setUV(
		float cu1, float cv1,
		float cu2, float cv2,
		float u, float v
	) {
		_numControlPts = 2;
		_u = u;
		_v = v;
		_cu1 = cu1;
		_cv1 = cv1;
		_cu2 = cu2;
		_cv2 = cv2;
		return this;
	}
	public HVertex x(float f) {
		return u(_path.x2u(f));
	}
	public float x() {
		return _path.u2x(_u);
	}
	public HVertex y(float f) {
		return v(_path.y2v(f));
	}
	public float y() {
		return _path.v2y(_v);
	}
	public HVertex z(float f) {
		return this;
	}
	public float z() {
		return 0;
	}
	public HVertex u(float f) {
		_u = f;
		return this;
	}
	public float u() {
		return _u;
	}
	public HVertex v(float f) {
		_v = f;
		return this;
	}
	public float v() {
		return _v;
	}
	public HVertex cx(float f) {
		return cx1(f);
	}
	public float cx() {
		return cx1();
	}
	public HVertex cy(float f) {
		return cy1(f);
	}
	public float cy() {
		return cy1();
	}
	public HVertex cu(float f) {
		return cu1(f);
	}
	public float cu() {
		return cu1();
	}
	public HVertex cv(float f) {
		return cv1(f);
	}
	public float cv() {
		return cv1();
	}
	public HVertex cx1(float f) {
		return cu1(_path.x2u(f));
	}
	public float cx1() {
		return _path.u2x(_cu1);
	}
	public HVertex cy1(float f) {
		return cv1(_path.y2v(f));
	}
	public float cy1() {
		return _path.v2y(_cv1);
	}
	public HVertex cu1(float f) {
		_cu1 = f;
		return this;
	}
	public float cu1() {
		return _cu1;
	}
	public HVertex cv1(float f) {
		_cv1 = f;
		return this;
	}
	public float cv1() {
		return _cv1;
	}
	public HVertex cx2(float f) {
		return cu2(_path.x2u(f));
	}
	public float cx2() {
		return _path.u2x(_cu2);
	}
	public HVertex cy2(float f) {
		return cv2(_path.y2v(f));
	}
	public float cy2() {
		return _path.v2y(_cv2);
	}
	public HVertex cu2(float f) {
		_cu2 = f;
		return this;
	}
	public float cu2() {
		return _cu2;
	}
	public HVertex cv2(float f) {
		_cv2 = f;
		return this;
	}
	public float cv2() {
		return _cv2;
	}
	public void computeMinMax(float[] minmax) {
		if(_u < minmax[0]) minmax[0] = _u;
		else if(_u > minmax[2]) minmax[2] = _u;
		if(_v < minmax[1]) minmax[1] = _v;
		else if(_v > minmax[3]) minmax[3] = _v;
		switch(_numControlPts) {
		case 2:
			if(_cu2 < minmax[0]) minmax[0] = _cu2;
			else if(_cu2 > minmax[2]) minmax[2] = _cu2;
			if(_cv2 < minmax[1]) minmax[1] = _cv2;
			else if(_cv2 > minmax[3]) minmax[3] = _cv2;
		case 1:
			if(_cu1 < minmax[0]) minmax[0] = _cu1;
			else if(_cu1 > minmax[2]) minmax[2] = _cu1;
			if(_cv1 < minmax[1]) minmax[1] = _cv1;
			else if(_cv1 > minmax[3]) minmax[3] = _cv1;
			break;
		default: break;
		}
	}
	public void adjust(float offsetU, float offsetV, float oldW, float oldH) {
		x( oldW*(_u += offsetU) ).y( oldH*(_v += offsetV) );
		switch(_numControlPts) {
		case 2: cx2( oldW*(_cu2 += offsetU) ).cy2( oldH*(_cv2 += offsetV) );
		case 1: cx1( oldW*(_cu1 += offsetU) ).cy1( oldH*(_cv1 += offsetV) );
			break;
		default: break;
		}
	}
	private float dv(float pv, float t) {
		switch(_numControlPts) {
		case 1: return HMath.bezierTangent(pv,_cv1,_v, t);
		case 2: return HMath.bezierTangent(pv,_cv2,_cv2,_v, t);
		default: return _v - pv;
		}
	}
	public boolean intersectTest(
		HVertex pprev, HVertex prev,
		float tu, float tv, boolean openPath
	) {
		float u1 = prev._u;
		float v1 = prev._v;
		float u2 = _u;
		float v2 = _v;
		if(isLine() || openPath) {
			return ((v1<=tv && tv<v2) || (v2<=tv && tv<v1)) && 
				tu < (u1 + (u2-u1)*(tv-v1)/(v2-v1));
		} else if(isQuadratic()) {
			boolean b = false;
			float[] params = new float[2];
			int numParams = HMath.bezierParam(v1,_cv1,v2, tv, params);
			for(int i=0; i<numParams; ++i) {
				float t = params[i];
				if(0<t && t<1 && tu<HMath.bezierPoint(u1,_cu1,u2, t)) {
					if(HMath.bezierTangent(v1,_cv1,v2, t) == 0) continue;
					b = !b;
				} else if(t==0 && tu<u1) {
					float ptanv = prev.dv(pprev._v,1);
					if(ptanv==0) ptanv = prev.dv(pprev._v,0.9375f);
					float ntanv = HMath.bezierTangent(v1,_cv1,v2, 0);
					if(ntanv==0) ntanv=HMath.bezierTangent(v1,_cv1,v2, 0.0625f);
					if(ptanv<0 == ntanv<0) b = !b;
				}
			}
			return b;
		} else {
			boolean b = false;
			float[] params = new float[3];
			int numParams = HMath.bezierParam(v1,_cv1,_cv2,v2, tv, params);
			for(int i=0; i<numParams; ++i) {
				float t = params[i];
				if(0<t && t<1 && tu<HMath.bezierPoint(u1,_cu1,_cu2,u2, t)) {
					if(HMath.bezierTangent(v1,_cv1,_cv2,_v, t) == 0) {
						float ptanv = HMath.bezierTangent(
							v1,_cv1,_cv2,v2, Math.max(t-0.0625f, 0));
						float ntanv = HMath.bezierTangent(
							v1,_cv1,_cv2,v2, Math.min(t+.0625f, 1));
						if(ptanv<0 != ntanv<0) continue;
					}
					b = !b;
				} else if(t==0 && tu<u1) {
					float ptanv = prev.dv(pprev._v,1);
					if(ptanv==0) ptanv = prev.dv(pprev._v,0.9375f);
					float ntanv = HMath.bezierTangent(v1,_cv1,_cv2, 0);
					if(ntanv==0) ntanv = HMath.bezierTangent(
						v1,_cv1,_cv2,v2, 0.0625f);
					if(ptanv<0 == ntanv<0) b = !b;
				}
			}
			return b;
		}
	}
	public boolean inLine(HVertex prev, float relX, float relY) {
		float x1 = prev.x();
		float y1 = prev.y();
		float x2 = x();
		float y2 = y();
		if(isLine()) {
			float diffv = y2-y1;
			if(diffv == 0) {
				return HMath.isEqual(relY, y1, LINE_TOLERANCE) &&
					( (x1<=relX && relX<=x2)||(x2<=relX && relX<=x1) );
			}
			float t = (relY-y1) / diffv;
			return (0<=t && t<=1) &&
				HMath.isEqual(relX, x1+(x2-x1)*t, LINE_TOLERANCE);
		} else if(isQuadratic()) {
			float[] params = new float[2];
			int numParams = HMath.bezierParam(y1,cy1(),y2, relY, params);
			for(int i=0; i<numParams; ++i) {
				float t = params[i];
				if(0<=t && t<=1) {
					float bzval = HMath.bezierPoint(x1,cx1(),x2, t);
					if(HMath.isEqual(relX, bzval, LINE_TOLERANCE)) return true;
				}
			}
			return false;
		} else {
			float[] params = new float[3];
			int numParams = HMath.bezierParam(y1,cy1(),cy2(),y2, relY, params);
			for(int i=0; i<numParams; ++i) {
				float t = params[i];
				if(0<=t && t<=1) {
					float bzval = HMath.bezierPoint(x1,cx1(),cx2(),x2, t);
					if(HMath.isEqual(relX, bzval, LINE_TOLERANCE)) return true;
				}
			}
			return false;
		}
	}
	public void draw(PGraphics g, float drawX, float drawY, boolean isSimple) {
		float drX = drawX + x();
		float drY = drawY + y();
		if(isLine() || isSimple) {
			g.vertex(drX, drY);
		} else if(isQuadratic()) {
			float drCX = drawX + cx1();
			float drCY = drawY + cy1();
			g.quadraticVertex(drCX,drCY, drX,drY);
		} else {
			float drCX1 = drawX + cx1();
			float drCY1 = drawY + cy1();
			float drCX2 = drawX + cx2();
			float drCY2 = drawY + cy2();
			g.bezierVertex(drCX1,drCY1, drCX2,drCY2, drX,drY);
		}
	}
	public void drawHandles(PGraphics g, HVertex prev,float drawX,float drawY) {
		if(isLine()) return;
		float x1 = drawX + prev.x();
		float y1 = drawY + prev.y();
		float x2 = drawX + x();
		float y2 = drawY + y();
		g.fill(HPath.HANDLE_FILL);
		g.stroke(HPath.HANDLE_STROKE);
		g.strokeWeight(HPath.HANDLE_STROKE_WEIGHT);
		if(isQuadratic()) {
			float drCX = drawX + cx1();
			float drCY = drawY + cy1();
			g.line(x1,y1, drCX,drCY);
			g.line(x2,y2, drCX,drCY);
			g.ellipse(drCX, drCY, HPath.HANDLE_SIZE,HPath.HANDLE_SIZE);
			g.fill(HPath.HANDLE_STROKE);
			g.ellipse(x1,y1, HPath.HANDLE_SIZE/2,HPath.HANDLE_SIZE/2);
			g.ellipse(x2,y2, HPath.HANDLE_SIZE/2,HPath.HANDLE_SIZE/2);
		} else {
			float drCX1 = drawX + cx1();
			float drCY1 = drawY + cy1();
			float drCX2 = drawX + cx2();
			float drCY2 = drawY + cy2();
			g.line(x1,y1, drCX1,drCY1);
			g.line(x2,y2, drCX2,drCY2);
			g.line(drCX1,drCY1, drCX2,drCY2);
			g.ellipse(drCX1, drCY1, HPath.HANDLE_SIZE,HPath.HANDLE_SIZE);
			g.ellipse(drCX2,drCY2, HPath.HANDLE_SIZE,HPath.HANDLE_SIZE);
			g.fill(HPath.HANDLE_STROKE);
			g.ellipse(x1,y1, HPath.HANDLE_SIZE/2,HPath.HANDLE_SIZE/2);
			g.ellipse(x2,y2, HPath.HANDLE_SIZE/2,HPath.HANDLE_SIZE/2);
		}
	}
}
/*
 * HYPE_Processing
 * http:
 * 
 * Copyright (c) 2013 Joshua Davis & James Cruz
 * 
 * Distributed under the BSD License. See LICENSE.txt for details.
 * 
 * All rights reserved.
 */
public static class HWarnings {
	public static final String
		NULL_TARGET =
		"A target should be assigned before using this method.",
		NO_PROTOTYPE =
		"This pool needs at least one prototype before requesting.",
		NULL_ARGUMENT =
		"This method does not take null arguments.",
		INVALID_DEST =
		"The destination doesn't not belong to any parent.",
		DESTCEPTION =
		"The destination cannot be itself",
		CHILDCEPTION =
		"Can't add this parent as its own child.",
		INVALID_CHILD =
		"The child you're trying to add is cannot be added to this drawable."
		;
	public static void warn(String type, String loc, String msg) {
		PApplet app = H.app();
		app.println("[Warning: "+type+" @ "+loc+"]");
		if( msg!=null && msg.length()>0 ) app.println("\t"+msg);
	}
	private HWarnings() {}
}
  public void settings() { 	size(500,500); 	smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "build" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
