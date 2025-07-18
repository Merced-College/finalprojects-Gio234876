# cpsc39-finalProjects

Upload your final project to this github repo.

Make a README file for your project and put the infor about your project in it - your name, date and what your program does.

Jose Tapia Lopez

07/18/2025

Final programming project- Uno game

The goal is to make a terminal based on the phycial card game Uno where the user can draw cards, place cards, 
sort yoru deck of cards, shuffle before every new game starts and assign a point system within to see who is winning. 



  /**
     * Sorts the player's hand by card number using insertion sort.
     */
    public void sortHandByNumber() {
        // Insertion Sort Algorithm
        for (int i = 1; i < hand.size(); i++) {
            UnoColorSelector.Card key = hand.get(i);
            int j = i - 1;
            while (j >= 0 && hand.get(j).getNumber() > key.getNumber()) {
                hand.set(j + 1, hand.get(j));
                j--;
            }
            hand.set(j + 1, key);
        }
    }