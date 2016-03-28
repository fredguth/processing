HCanvas c1;
HPixelColorist colors;
HSwarm swarm;
HDrawablePool pool;
HTimer timer;

void setup() {
	size(1280,640);
	H.init(this).background(#202020);
	smooth();

	colors = new HPixelColorist("color.jpg").fillOnly();

	c1 = new HCanvas(640,640).autoClear(false).fade(1);
	// c1.x(-10000);
	H.add(c1);

	H.add( new HImage("color.jpg") ).loc(640, 0);

	pool = new HDrawablePool(20);
	pool.autoParent(c1)
		.add (new HRect().rounding(4))

		.onCreate (
			new HCallback() {
				public void run(Object obj) {
					float rotation = random(TWO_PI);
					HDrawable d = (HDrawable) obj;
					d
						.size( 100,2 )
						.noStroke()
						.loc( 320, 320 )
						.rotationRad(rotation)
						.move(cos(rotation)*10, sin(rotation)*10)
						.anchorAt( H.CENTER )
					;
					colors.applyColor(d);

					new HSwarm()
						.addTarget(d)
						.addGoal(320,320)
						.speed(15)
						.turnEase(0.02)
						.twitch(5)
					;
				}
			}
		)
	;

	timer = new HTimer()
		.numCycles( pool.numActive() )
		.interval(250)
		.callback(
			new HCallback() { 
				public void run(Object obj) {
					pool.request();
				}
			}
		)
	;
}

void draw() {
	for(HDrawable d : pool) {
		if ( colors.getColor( d.x(),d.y() ) != 0 ) {
			// color if NOT black
			colors.applyColor(d);
		}
	}

	H.drawStage();
}

