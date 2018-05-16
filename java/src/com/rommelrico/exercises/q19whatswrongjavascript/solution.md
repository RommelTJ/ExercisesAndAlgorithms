### Solution

The prize is always undefined!

#### The Problem

The anonymous method we're assigning to the buttons' onclicks has access to variables in the scope 
outside of it (this is called a closure). In this case, it has access to btnNum.

When a method accesses a variable outside its scope, it accesses that variable, not a frozen copy. 
So when the value held by the variable changes, the method gets that new value. By the time the 
user starts pressing buttons, our loop will have already completed and btnNum will be 3, so this 
is what each of our anonymous methods will get for btnNum!

Why 3? The for loop will increment btnNum until the conditional in the middle is no longer met — 
that is, until it's not true that btnNum < prizes.length. So the code in the for loop won't run 
with btnNum = 3, but btnNum will be 3 when the loop is done.

Why undefined? prizes has 3 elements, but they are at indices 0, 1, 2. Array indices start at 0, 
remember? In JavaScript, accessing a nonexistent index in an array returns undefined.

#### The Solution

We can solve this by wrapping our anonymous method in another anonymous method that takes btnNum as 
an argument. Like so:

```html
<button id="btn-0">Button 1!</button>
<button id="btn-1">Button 2!</button>
<button id="btn-2">Button 3!</button>

<script type="text/javascript">
    var prizes = ['A Unicorn!', 'A Hug!', 'Fresh Laundry!'];
    for (var btnNum = 0; btnNum < prizes.length; btnNum++) {
        // for each of our buttons, when the user clicks it...
        document.getElementById('btn-' + btnNum).onclick = function(frozenBtnNum){
            return function() {
                // tell her what she's won!
                alert(prizes[frozenBtnNum]);
            };
        }(btnNum);  // LOOK! We're passing btnNum to our anonymous function here!
    }
</script>
```

This "freezes" the value of btnNum. Why? Well...

#### Primitives vs. Objects

btnNum is a number, which is a primitive type in JavaScript.

Primitives are "simple" data types (string, number, boolean, null, and undefined in JavaScript). 
Everything else is an object in JavaScript (methods, arrays, Date() values, etc).

#### Arguments Passed by Value vs. Arguments Passed by Reference

One important property of primitives in JS is that when they are passed as arguments to a method, 
they are copied ("passed by value"). So for example:

```javascript
var threatLevel = 1;

function inspireFear(threatLevel){
    threatLevel += 100;
}

inspireFear(threatLevel);
console.log(threatLevel);  // Whoops! It's still 1!
```

The threatLevel inside inspireFear() is a new number, initialized to the same value as the 
threatLevel outside of inspireFear(). Giving these different variables the same name might cause 
confusion here. If we change the two variables to have different names we get the exact same 
behavior:

```javascript
var threatLevel = 1;

function inspireFear(theThreatLevel){
    theThreatLevel += 100;
}

inspireFear(threatLevel);
console.log(threatLevel);  // Whoops! It's still 1!
```

In contrast, when a method takes an object, it actually takes a reference to that very object. So 
changes you make to the object in the method persist after the method is done running. This is 
sometimes called a side effect.

```javascript
var scaryThings = ['spiders', 'Cruella de Vil'];

function inspireFear(scaryThings){
    scaryThings.push('nobody ever using Interview Cake');
    scaryThings.push('i should have gotten a real job');
    scaryThings.push('why am i doing this to myself');
}

inspireFear(scaryThings);
console.log(scaryThings);
// ['spiders', 'Cruella de Vil', 'nobody ever using Interview Cake', 'i should have gotten a real job', 'why am i doing this to myself']
```

#### Bringing it home

Back to our solution:

```html
<button id="btn-0">Button 1!</button>
<button id="btn-1">Button 2!</button>
<button id="btn-2">Button 3!</button>

<script type="text/javascript">
    var prizes = ['A Unicorn!', 'A Hug!', 'Fresh Laundry!'];
    for (var btnNum = 0; btnNum < prizes.length; btnNum++) {
        // for each of our buttons, when the user clicks it...
        document.getElementById('btn-' + btnNum).onclick = function(frozenBtnNum){
            return function() {
                // tell her what she's won!
                alert(prizes[frozenBtnNum]);
            };
        }(btnNum);
    }
</script>
```

So when we pass btnNum to the outer anonymous method as its one argument, we create a new number 
inside the outer anonymous method called frozenBtnNum that has the value that btnNum had at that 
moment (0, 1, or 2).

Our inner anonymous method is still a closure because it still reaches outside its scope, but now 
it closes over this new number called frozenBtnNum, whose value will not change as we iterate 
through our for loop.

### What We Learned

Like several common Javascript interview questions, this question hinges on a solid understanding 
of closures and pass by reference vs pass by value. If you're shaky on either of those, look back 
at the examples in the solution.
