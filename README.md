<h1>TextAnalyzer</h1>
<h2>Installation/Usage</h2>

```
git clone https://github.com/seagullmouse/textanalyzer.git
cd textanalyzer
mvn clean install
java -jar target/TextAnalyzer.jar -f ~/Documents/Temp/textfiles/bible_daily.txt
```

<h2>Assumptions</h2>

* Trailing punctuation is removed from words, e.g. "morning." becomes "morning"
* Punctuation that is not trailing is maintained, e.g. "&" is considered a word
* Punctuation within words is maintained, e.g. "200.00", "ta-ta", "2020/01/01" are considered words
* An error is printed for empty files
* Only tested for texts in English
* Java 11
