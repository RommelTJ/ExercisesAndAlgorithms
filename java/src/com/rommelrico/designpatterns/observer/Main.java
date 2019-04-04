package com.rommelrico.designpatterns.observer;

import com.rommelrico.designpatterns.observer.interfaces.*;
import com.rommelrico.designpatterns.observer.models.*;

public class Main {

    public static void main(String[] args) {
        EmailTopic emailTopic = new EmailTopic();

        // Create Observers
        Observer firstObserver = new EmailTopicSubscriber("FirstObserver");
        Observer secondObserver = new EmailTopicSubscriber("SecondObserver");
        Observer thirdObserver = new EmailTopicSubscriber("ThirdObserver");

        // Register Observers
        emailTopic.register(firstObserver);
        emailTopic.register(secondObserver);
        emailTopic.register(thirdObserver);

        // Attaching observer to subject
        firstObserver.setSubject(emailTopic);
        secondObserver.setSubject(emailTopic);
        thirdObserver.setSubject(emailTopic);

        // Check for updates
        firstObserver.update();

        // Provider/Subject (broadcaster)
        emailTopic.postMessage("Hello Subscribers");

        // Unregister
        emailTopic.unregister(firstObserver);
        emailTopic.postMessage("Hello Subscribers Again");

    }
}
