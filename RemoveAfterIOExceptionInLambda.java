
/**
 * Remove object from the list when causes IOException, after lambda forEachRemaining.
 *
 * @author	BingLi224
 */
import java.util.Arrays;
import java.util.ArrayList;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.IOException;

public class RemoveAfterIOExceptionInLambda
{
	public static void main(String[] args)
	{
		try (
			OutputStream o1 = new ByteArrayOutputStream ( );
		) {
			// list of object to test the lambda
			ArrayList <OutputStream> os = new ArrayList <OutputStream> ( );
			os.add ( new ByteArrayOutputStream ( ) );
			os.add ( new ByteArrayOutputStream ( ) );
			os.add ( o1 );

			// objects to be removed after lambda
			ArrayList <OutputStream> toRemove = new ArrayList <OutputStream> ( );

			os.iterator ( ).forEachRemaining ( o -> {
					try {
						// throw IOException, intentionally for removing it from the ArrayList
						throw new IOException ( );
					} catch ( IOException ioe ) {
						ioe.printStackTrace ( );

						// note to be removed after lambda
						toRemove.add ( o );
					}
				}
			);

			// count of objects before remove
			System.out.println ( "before=" + os.size ( ) );

			// remove all that caused IOException
			os.removeAll ( toRemove );

			// count of objects after remove
			System.out.println ( "after=" + os.size ( ) );
		}
		catch ( Exception e ) { e.printStackTrace ( ); }
	}
}
