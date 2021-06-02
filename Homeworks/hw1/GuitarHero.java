/** A client that uses the synthesizer package to replicate a keyboard. */
import es.datastructur.synthesizer.GuitarString;

public class GuitarHero {
    private static final String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    private static GuitarString[] keys = new GuitarString[37];

    private static void createKeys() {
        for (int i = 0; i < 37; i++) {
            keys[i] = new GuitarString(440 * Math.pow(2, (i - 24) / 12));
        }
    }

    public static void play() {
        createKeys();
        while (true) {
            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int key_index = keyboard.indexOf(key);
                if (key_index == -1) {
                    continue;
                }
                keys[key_index].pluck();
            }

            /* compute the superposition of samples */
            double sample = 0;
            for (GuitarString k1 : keys) {
                sample += k1.sample();
            }

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            for (GuitarString k2 : keys) {
                k2.tic();
            }
        }
    }

    public static void main (String[]args){
        GuitarHero.play();
    }
}

