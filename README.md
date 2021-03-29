# Company Internship Programming Challenge 1  

I did this for a programing challenge, and I'm proud enough of the work  
I did on it to upload it.

This is basically a test of linked list operations. The drops.modify  
method has 3 modes, add, swap, and remove. The method takes in 3  
arguments. A string that shows a linked list like the following.  

> A->B->C, E->F->G  

The comma separates the string into two linked lists that have to be parsed  
for the letters between the arrows. Then there are the operands, a string  
that has 1 or 2 letters separated by a comma. Finally, there's the actual  
operation.


### Add  

Takes two operands and adds the 2nd operands and its children to after  
the first operand.  

### Swap  

Takes two operands. Swaps the position of two nodes. This doesn't take children  
with it.  

### Remove  

Takes one operand. Completely deletes a node, its children going to the  
parent if there is one.  

### Other Stuff  

At the end of doing the operations the new linked lists will also be checked  
for loops. If there is one an error will be output.  
