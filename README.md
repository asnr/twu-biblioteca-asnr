
Program design
--------------

The app is structured around a cheap and cheerful ModelViewController implementation.

For the view, each app screen is it's own class that implements a printScreen() function.

The controller is a finite state machine takes care of processing user input to update the model and move between screens.

The model models.


Run and test
------------

To run, run BibliotecaApp class from inside Intellij IDEA.

To test, run all the tests in the test/com/twu/biblioteca directory from inside Intellij IDEA.

I guess you should be able to test and run from the console, but I'm going to bed now.


Instructions to push your code to github.com
--------------------------------------------

1. Sign into GitHub and create a new repository
        Repository name: twu-biblioteca-yourname
        Privacy level: Public
        Don't check "Initialize this repository with a README"
        Add .gitignore: None
        Add a license: Apache License 2.0

2. Run 'git init' in the folder that was extracted from the downloaded .zip file, in order to
 initialize local repository.

3. Make a local commit.
    $ git add -A
    $ git commit -m “Initial commit”

(Dont forget to add the hidden .idea folder, as it will make opening your project in Intellij easier.)

4. After you are done with the local commit, you can share your code with "The World" by pushing it to your
 GitHub repository.
    $ git remote add origin https://github.com/<YOUR-GITHUB-USERNAME>/twu-biblioteca-<YOURNAME>
    $ git push origin master

Instructions to open your code in Intellij
------------------------------------------

1. Go to File -> Open (or Open Project)
2. Select the directory containing the code
DONE !

