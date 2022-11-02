

runDataWranglerTests: Book.java BookLoader.java DataWranglerTest.java
	javac Book.java
	javac BookLoader.java
	javac DataWranglerTest.java
	java DataWranglerTest.java

runFrontendDeveloperTests: FDcompile
	java FrontendDeveloperTest

FDcompile: FDISBNValidator.class FDBookMapperBackend.class BookMapperFrontend.class TextUITester.class FrontendDeveloperTest.class

FDISBNValidator.class: FDISBNValidator.java
	javac FDISBNValidator.java

FDBookMapperBackend.class: FDBookMapperBackend.java
	javac FDBookMapperBackend.java

BookMapperFrontend.class: BookMapperFrontend.java
	javac BookMapperFrontend.java

TextUITester.class: TextUITester.java
	javac TextUITester.java

FrontendDeveloperTest.class: FrontendDeveloperTest.java
	javac FrontendDeveloperTest.java


runAlgorithmEngineerTests: AEcompile
	java AlgorithmEngineerTest

AEcompile: ISBNChecker.class HashtableMap.class MapADT.class IteratorHashTable.class IterableMapADT.class Element.class AlgorithmEngineerTest.class

ISBNChecker.class: ISBNChecker.java
	javac ISBNChecker.java

HashtableMap.class: HashtableMap.java
	javac HashtableMap.java

MapADT.class: MapADT.java
	javac MapADT.java

IteratorHashTable.class: IteratorHashTable.java
	javac IteratorHashTable.java

IterableMapADT.class: IterableMapADT.java
	javac IterableMapADT.java

Element.class: Element.java
	javac Element.java

AlgorithmEngineerTest.class: AlgorithmEngineerTest.java
	javac AlgorithmEngineerTest.java

runBackendDeveloperTests: BackendTesters.class
	java BackendTesters

BackendTesters.class: BackendTesters.java
	javac BackendTesters.java
clean:
	rm *.class
