package com.seniv.VotingSystem;

public class Borda extends VotingSystem {

    public Borda() {
    }

    public Borda(Ballot[] ballots) {
        super(ballots);
    }

    protected void setVotes() {
        for (Ballot ballot : voterBallots) {
            for (String candidate : ballot.toArray()) {
                int score = candVotes.get(candidate) +
                        candVotes.size() - ballot.getPosition(candidate) + 1;

                candVotes.put(candidate, score);
            }
        }
    }
}//end Borda