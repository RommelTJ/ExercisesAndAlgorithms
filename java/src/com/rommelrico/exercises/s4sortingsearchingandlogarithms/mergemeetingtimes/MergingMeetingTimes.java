package com.rommelrico.exercises.s4sortingsearchingandlogarithms.mergemeetingtimes;

import java.util.*;

/**
 * Solution
 *
 * First, we sort our input list of meetings by start time so any meetings that
 * might need to be merged are now next to each other.
 *
 * Then we walk through our sorted meetings from left to right. At each step,
 * either:
 *
 * 1. We can merge the current meeting with the previous one, so we do.
 * 2. We can't merge the current meeting with the previous one, so we know the
 *      previous meeting can't be merged with any future meetings and we throw
 *      the current meeting into mergedMeetings.
 *
 * Complexity
 *
 * O(n lg n) time and O(n) space.
 *
 * Even though we only walk through our list of meetings once to merge them, we
 * sort all the meetings first, giving us a runtime of O(n lg n). It's worth
 * noting that if our input were sorted, we could skip the sort and do this in
 * O(n) time!
 *
 * We create a new list of merged meeting times. In the worst case, none of the
 * meetings overlap, giving us a list identical to the input list. Thus we have
 * a worst-case space cost of O(n).
 *
 * What We Learned
 *
 * This one arguably uses a greedy approach as well, except this time we had
 * to sort the list first.
 *
 * How did we figure that out?
 *
 * We started off trying to solve the problem in one pass, and we noticed that
 * it wouldn't work. We then noticed the reason it wouldn't work: to see if a
 * given meeting can be merged, we have to look at all the other meetings!
 * That's because the order of the meetings is random.
 *
 * That's what got us thinking: what if the list were sorted? We saw that then
 * a greedy approach would work. We had to spend O(n lg n) time on sorting the
 * list, but it was better than our initial brute force approach, which cost us
 * O(n^2) time!
 *
 */
public class MergingMeetingTimes {
    private static List<Meeting> sampleRange = Arrays.asList(
            new Meeting(0, 1),
            new Meeting(3, 5),
            new Meeting(4, 8),
            new Meeting(10, 12),
            new Meeting(9, 10)
    );

    private static List<Meeting> mergeRanges(List<Meeting> meetings) {

        // make a copy so we don't destroy the input
        List<Meeting> sortedMeetings = new ArrayList<>();
        for (Meeting meeting: meetings) {
            Meeting meetingCopy = new Meeting(meeting.getStartTime(), meeting.getEndTime());
            sortedMeetings.add(meetingCopy);
        }

        // sort by start time
        Collections.sort(sortedMeetings, new Comparator<Meeting>() {
            @Override
            public int compare(Meeting m1, Meeting m2)  {
                return m1.getStartTime() - m2.getStartTime();
            }
        });

        // initialize mergedMeetings with the earliest meeting
        List<Meeting> mergedMeetings = new ArrayList<>();
        mergedMeetings.add(sortedMeetings.get(0));

        for (Meeting currentMeeting : sortedMeetings) {

            Meeting lastMergedMeeting = mergedMeetings.get(mergedMeetings.size() - 1);

            // if the current and last meetings overlap, use the latest end time
            if (currentMeeting.getStartTime() <= lastMergedMeeting.getEndTime()) {
                lastMergedMeeting.setEndTime(Math.max(lastMergedMeeting.getEndTime(), currentMeeting.getEndTime()));

                // add the current meeting since it doesn't overlap
            } else {
                mergedMeetings.add(currentMeeting);
            }
        }

        return mergedMeetings;
    }

    public static void main(String[] args) {
        List<Meeting> mergedList = mergeRanges(sampleRange);
        System.out.println(mergedList.toString());
    }

}
