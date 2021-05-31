// https://fa20.datastructur.es/materials/discussion/disc02.pdf

/* 1.   a. Java would print "Name : Pikachu, Level: 100"
        b. We mean the local variable containing the parameter to the change method.
*/

/* 2.   a. First call to a.play() will print "Nyan! I'm Cream the cat!"
           First call to b.play() will print "Nyan! I'm Tubbs the cat!"  // Instances a and b will have their own copies of the instance variable name, but will share the static variable noise.
           Second call to a.play() will print "nyan! I'm Cream the cat!"
           Second call to b.play() will print "nyan! I'm Tubbs the cat!" // Static members can be accessed and changed by instances but not the other way round.
           Third call to a.play() will print "nyan! I'm Kitty the cat!"
           Third call to b.play() will print "nyan! I'm Tubbs the cat!"

         b. Cat class cannot call nickname method because nickname is a non-static (instance) method which can only be called by an instance.
*/

// 4. Already done in Lab 2.