
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MetaCalculator 
{
	private int avgWordLength;
	private float avgSentLength;
	private float wrdSntLngthRatio;
	private Map<Character, Float> relLetterFreq;
	private float richness;
	private float ltrPairFreq;			//***************NEED TO CHECK ON WHAT THIS IS**********************

	private int numWords;
	
	MetaCalculator()
	{
		super();
		
	}
	MetaCalculator(List<String> tArray)
	{
		this.avgWordLength = calcAvgWordLength(tArray);
		this.numWords = calcNumWords(tArray);
		//this.wrdSntLngthRatio = calcWordPerSnt(tArray);
		this.relLetterFreq = calcRelLtrFreq(tArray);
		this.richness = calcRichness(tArray);
		this.avgSentLength = calcWordPerSnt(tArray);
	}
	
	/*
	 * ratio of word length to sentence length
	 * each sentence has a different ratio. 
	 * returns total all ratios / num sentences
	 */
	public void calcWrdLnSentLnRatio(List<String> textArray)
	{
		Pattern p = Pattern.compile("[\\w]+\\p{Punct}");		//a word with a punctuation mark. 
		Matcher m;
		for(String s: textArray)
		{			
			m = p.matcher(s);
			if(m.matches())
			{
				
			}
		}
		
	}
	
	/*
	 * returns a float = number of unique words / total number of words
	 */
	public float calcRichness(List<String> tArray)
	{
		float uniqWordCount = 0;
		for(String s : tArray)
		{
			int wordIdx = tArray.indexOf(s);
			int wordCopyIdx = tArray.lastIndexOf(s);
			if(wordIdx != wordCopyIdx)				//the same word is at two indices, it is not unique
			{
				++uniqWordCount;
			}
			
		}
		if(tArray.isEmpty())
			return 0;
		return (uniqWordCount / (float)tArray.size());
	}
	
	/*
	 * returns a map of each letter (a-z) mapped to it's relative freq.
	 * relative freq = total number of occurrences of a letter / total number of letters.
	 * disregards capitalization
	 */
	public Map<Character, Float> calcRelLtrFreq(List<String> textArray)
	{
		Map<Character, Float> freqMap = new HashMap<Character, Float>();
		int letterCount = 0;						//total number of letters in text
		String letters = "abcdefghijklmnopqrstuvwxyz";
		for(int i = 0; i< letters.length(); i++)	//init map to 0's
		{
			freqMap.put(letters.charAt(i), (float)0);
		}
		for(String word: textArray)
		{													//put freq of each char in map
			word = word.replaceAll("[^A-za-z]", "");		//allow only chars in words to calc letter freq
			for(int i = 0; i< word.length(); i++)
			{
				Character c = word.toLowerCase().charAt(i);
				float freq = freqMap.get(c);
				freqMap.put(c, ++freq);
				++letterCount;
			}
		}
		for (Character c: freqMap.keySet())					//divide each by letterCount to get relative freq
		{
			Float relFrq = freqMap.get(c) / letterCount;
			freqMap.put(c, relFrq);
		}
		return freqMap;
	}

	/*
	 * returns average word length for textArray
	 */
	public int calcAvgWordLength(List<String> textArray)
	{
		int wordCount = 0;
		int totalChars = 0;
		int avg = 0;
		for(String s: textArray)
		{
			totalChars += s.length();
			++wordCount;
		}
		if(!(wordCount == 0))
			avg = totalChars / wordCount;
			
		return avg;
	}
	public int calcNumWords(List<String> textArray)
	{
		return textArray.size();
	}
	/*
	 * Avg number of words per sentence.
	 */
	public float calcWordPerSnt(List<String> textArray)
	{
		float avgWdsPerSent=0;
		float numSent = 0;
		
		Pattern p = Pattern.compile("[\\w]+\\p{Punct}");		//a word with a punctuation mark. 
		Matcher m;
		for(String s: textArray)
		{			
			m = p.matcher(s);
			if(m.matches())
			{
				numSent++;
			}
		}
		avgWdsPerSent = (textArray.size()/numSent);
		return avgWdsPerSent;
	}
	
	public int getNumWords() {
		return numWords;
	}
	private void setNumWords(int numWords) {
		this.numWords = numWords;
	}
	public int getAvgWordLength() {
		return avgWordLength;
	}
	public void setAvgWordLength(int avgWordLength) {
		this.avgWordLength = avgWordLength;
	}
	public float getAvgSentLength() {
		return avgSentLength;
	}
	public void setAvgSentLength(int avgSentLength) {
		this.avgSentLength = avgSentLength;
	}
	public float getWrdSntLngthRatio() {
		return wrdSntLngthRatio;
	}
	public void setWrdSntLngthRatio(float wrdSntLngthRatio) {
		this.wrdSntLngthRatio = wrdSntLngthRatio;
	}
	public Map<Character, Float> getRelLetterFreq() {
		return relLetterFreq;
	}
	public void setRelLetterFreq(Map<Character, Float> relLetterFreq) {
		this.relLetterFreq = relLetterFreq;
	}
	public float getRichness() {
		return richness;
	}
	public void setRichness(float richness) {
		this.richness = richness;
	}
	public float getLtrPairFreq() {
		return ltrPairFreq;
	}
	public void setLtrPairFreq(float ltrPairFreq) {
		this.ltrPairFreq = ltrPairFreq;
	}
}