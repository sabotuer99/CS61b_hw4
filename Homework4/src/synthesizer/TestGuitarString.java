package synthesizer;

/* Since this test is part of a package, we have to import the package version of StdAudio. */
/* Don't worry too much about this, we'll get there in due time. */
import edu.princeton.cs.introcs.StdAudio;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the GuitarString class.
 * 
 * @author Josh Hug
 */

public class TestGuitarString {
	@Test
	public void testPluckTheAString() {
		double CONCERT_A = 440.0;
		GuitarString aString = new GuitarString(CONCERT_A);

		for (int j = 0; j < 1; j++) {
			aString.pluck();
			for (int i = 0; i < 10000; i += 1) {
				StdAudio.play(aString.sample());
				aString.tic();
			}
		}

	}

	/** Calls tests for GuitarString. */
	public static void main(String[] args) {
		jh61b.junit.textui.runClasses(TestGuitarString.class);
	}
}