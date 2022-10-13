class Bookshelf{
    private Book[] BookList = new Book[20];
    // private int NextEmpty = 0;
    // for(int i = 0; i < this.BookList.length; i ++){
    //     if(this.BookList[i] == null){
    //         NextEmpty = i;
    //         break;}}
    public Bookshelf(int size) {
        this.BookList = new Book[size];
    }
    public Bookshelf(Book[] initBooks){
        this.BookList = initBooks;
    }

    /**
     * adds new book at next open spot
     * @param Newbook book to be added
     * @return true if book is successfully added, false if no open spots are found
     */
    public boolean add(Book Newbook){
        for (int i = 0; i < this.BookList.length; i++){
            if(this.BookList[i] == null){
                this.BookList[i] = Newbook;
                return true;
            }
        }
        return false;
    }

    /**
     * makes a Bookshelf containing just the ones by the author specified
     * @param Author author to be specified
     * @return bookshelf with just the books by the author, shelf will be empty if none are found
     */
    public Bookshelf getBooksByAuthor(String Author){
        int numOccur = 0;
        for (int i = 0; i < this.BookList.length; i++){
            if(this.BookList[i].getAuthor().equals(Author)){
                numOccur += 1;
            }
        }
        Book[] AuthorList = new Book[numOccur];
        int placeHolder = 0;
        for (int i = 0; i < this.BookList.length; i++){
            if(this.BookList[i].getAuthor().equals(Author)){
                AuthorList[placeHolder] = this.BookList[i];
                placeHolder += 1;
            }
        }
        Bookshelf AuthorShelf = new Bookshelf(AuthorList);
        return AuthorShelf;
    }

    /**
     * prints a list of all books on the shelf by Title, Author, and Rating, each book on its own line
     */
    public String toString(){
        String wurds = "";
        for (int i = 0; i < this.BookList.length; i ++){
            if(this.BookList[i] != null){
            String temp = this.BookList[i].toString() + "\n";
            wurds = wurds + temp;
            }
        }
        return wurds;
    }

    /**
     * sorts list of books by specified paramater
     * @param sortBy a character that specifies how the list is sorted; Author, Title, or Rating
     * returns nothing, only edits and organizes the list itself
     */
    public void sort(char sortBy){
        System.out.println("running");
        int actualLength = this.BookList.length;
        for (int z = 0; z < this.BookList.length; z++){
            if(this.BookList[z] == null){
                actualLength = actualLength - 1;
            }
        }

        int i, j, minIndex;
        Book temp;
        for (i = 0; i < actualLength; i++){
            minIndex = i;
            //System.out.println("i author " + this.BookList[minIndex].getAuthor());
            for (j = i+1; j < actualLength; j++){
                //System.out.println("    j author " + this.BookList[j].getAuthor());
                if(this.BookList[minIndex].compareTo(this.BookList[j], sortBy) > 0){
                    minIndex = j;
                }
            }
            temp = this.BookList[minIndex];
            this.BookList[minIndex] = this.BookList[i];
            this.BookList[i] = temp;
        }

        // int i, j;
        // Book temp;
        // for(i = 0; i < actualLength; i++){
        //     System.out.println("i author " + this.BookList[i].getAuthor());
        //     for(j = i+1; j < actualLength; j++){
        //         System.out.println("    j author " + this.BookList[j].getAuthor());
        //         //System.out.println(this.BookList[j].compareTo(this.BookList[minIndex], sortBy));
        //         // flip sign to make backwards
        //         if (this.BookList[i].compareTo(this.BookList[j], sortBy) > 0){
        //             temp = this.BookList[i];
        //             this.BookList[i] = this.BookList[j];
        //             this.BookList[j] = temp;
        //         }
        //     }
        // }
    }
    

    public static void main(String[] args){
        // Bookshelf bs = new Bookshelf(5);
        // bs.add(new Book("Eragon", "Christopher Paolini", 10.0));
        // bs.add(new Book("Eldest", "Christopher Paolini", 10.0));
        // bs.add(new Book("Brisingr", "Christopher Paolini", 10.0));
        // bs.add(new Book("Inheritance", "Christopher Paolini", 10.0));
        // bs.add(new Book("Dracula", "Bram Stoker", 7.5));
        // Bookshelf goodbooks = bs.getBooksByAuthor("Christopher Paolini");
        // System.out.println(goodbooks);

        Bookshelf bs = new Bookshelf(5);
    bs.add(new Book("Eragon", "Christopher Paolini", 10.0));

    bs.add(new Book("The Fellowship of the Ring", "J.R.R. Tolkein", 10.0));
    bs.add(new Book("Twilight", "Stephenie Meyer", 0.0));
    bs.add(new Book("The Diary of a Wimpy Kid", "Jeff Kinney", 0.0));
    bs.add(new Book("Dracula", "Bram Stoker", 7.5));

    // System.out.println(bs.sort('a'));
    bs.sort('a');
    System.out.println(bs);
    }
}