## How to contribute

### Git-Related

#### **Did you find a bug?** / **Do you want to suggest something?**

* Create an issue at [this issue page](https://github.com/KilianPAGEOT/DashFrame/issues).

#### **Do you want to create a branch?**

* Your branch name should be formatted as `fix/<ISSUENUMBER>-<TITLE>` for bug fixes or `feature/<ISSUENUMBER>-<TITLE>` for features, example: `fix/4221-infinite-loop`.

#### **Do you want to fix an issue?**

* Create a branch

* Implement your features of fixes in it.

* Submit a [pull request](https://github.com/KilianPAGEOT/DashFrame/pulls).

* Once validated, merge to PR to `master` and remove the source branch (with `git branch -D <branch_name>`.

#### **How to title commits?**

* Follow the guidelines at https://cbea.ms/git-commit/

* Use imperative tense (avoid past tense).

* The title of the commit must be a summuary of the content and not be too long (less than 50 characters).

* Prefer putting detailed informations inside the commit's description.

* Example:
  ```sh
  $> git commit -m 'Fix infinite loop when pressing Alt-F4
  
  This was caused by a missing check in the event loop
  The program now checks when the window is set to close'
  ```

***

### Code Documentation
