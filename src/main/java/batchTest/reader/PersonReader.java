package batchTest.reader;

import batchTest.core.Personne;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.UnexpectedInputException;

import java.text.ParseException;

public class PersonReader implements ItemReader {
	static Personne[] personArray = new Personne[100];
	static {
		for (int i = 0; i < 100; i++) {
			personArray[i] = new Personne();
		}
	}
	static int readIndex = -1;
	public void mark() {
		readIndex++;
	}
	public Object read() throws UnexpectedInputException, ParseException {
		if (readIndex>=personArray.length) {
			return null;
		}
		return personArray[readIndex];
	}
	public void reset(){
	}
}