package cscmatch;

import java.io.Serializable;

public class Compatibility implements Comparable<Compatibility>, Serializable {

    private Member otherMbr;
    private int score;

    public Compatibility(Member myMbr, Member otherMbr) {

        this.otherMbr = otherMbr;
        this.score = 0;

        for (Interest myIntr : myMbr) {
            Interest otherIntr = otherMbr.findInterest(myIntr.getTopic());

            if (otherIntr != null) {
                score += myIntr.getLevel() * otherIntr.getLevel();
            }

        }

        for (Interest otherIntr : otherMbr) {
            Interest myIntr = myMbr.findInterest(otherIntr.getTopic());

            if (myIntr == null) {
                score += otherIntr.getLevel() / 2;
            }
        }
    }

    public int getScore() {
        return score;
    }

    public Member getOtherMbr() {
        return otherMbr;
    }

    public int compareTo(Compatibility otherCmpt) {
        if (this.score != otherCmpt.score) {
            return Integer.compare(otherCmpt.score, this.score);
        }
        return this.otherMbr.getName().compareTo(otherCmpt.otherMbr.getName());
    }

    public String toString() {
        return String.format("%s: %d", otherMbr.getName(), score);
    }
}
