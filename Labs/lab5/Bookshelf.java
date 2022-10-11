class Bookshelf{
    private Book[] BookList = new Book[20];
    public Bookshelf(int size) {
        Book[] Booklist = new Book[size];
    }
    
    public boolean add(Book Newbook){
        for (int i = 0; i < this.BookList.length; i++){
            if(this.BookList[i] == null){
                this.BookList[i] = Newbook;
                return true;
            }
        }
        return false;
    }
    
}