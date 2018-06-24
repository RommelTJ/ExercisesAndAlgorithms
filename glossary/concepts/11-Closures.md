# Closures

A closure is a method that accesses a variable "outside" itself. For example:

```
var message = 'The British are coming.';
function sayMessage(){
    alert(message);  // here we have access to message,
    // even though it's declared outside this function!
}
```

We'd say that message is "closed over" by sayMessage().

One useful thing to do with a closure is to create something like an "instance variable" that can 
change over time and can affect the behavior of a method.

```
// function for getting the id of a dom element,
// giving it a new, unique id if it doesn't have an id yet
var getUniqueId = (function(){
    var nextGeneratedId = 0;
    return function(element) {
        if (!element.id) {
            element.id = 'generated-uid-' + nextGeneratedId;
            nextGeneratedId++;
        }
        return element.id;
    };
})();
```

Why did we put nextGeneratedId in an immediately-executed anonymous method? It makes nextGeneratedId 
private, which prevents accidental changes from the outside world:

```
// function for getting the id of a dom element,
// giving it a new, unique id if it doesn't have an id yet
var nextGeneratedId = 0;
var getUniqueId = function(element) {
    if (!element.id) {
        element.id = 'generated-uid-' + nextGeneratedId;
        nextGeneratedId++;
    }
    return element.id;
};

// ...
// somewhere else in the codebase...
// ...

// WHOOPS--FORGOT I WAS ALREADY USING THIS FOR SOMETHING
nextGeneratedId = 0;
```

