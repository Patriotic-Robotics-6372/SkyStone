## Android Studio to GitHub
- Check that git is installed on your computer
- Have a github account
- File
- Settings 
- Version Control
- GitHub
- Sign in
- VCS 
- Import into version control
- Share project on GitHub
- Select Skystone-master file
- Enter repo name, desc, and share

## Commit from Android Studio to GitHub
- Go to Terminal
- Type in:

'''powershell
terminal git remote add origin https://github.com/Patriotic-Robotics-6372/SkyStone
'''

'''powershell
git add --all
'''

'''powershell
git commit -m "Commit message goes here"
'''

'''powershell
git push origin master
'''

* If this does not work, try putting Git under Program Files or changing the PATH variable in windows so that "git" refers to the git program.

## GitHub to Android Studio 
- File
- New
- Project from Version Control
- GitHub
- Select this repo: https://github.com/Patriotic-Robotics-6372/SkyStone

## Updating changes made in GitHub to Android Studio
- Go to Terminal
- Type in: 
'''powershell 
git pull origin master
'''

__Jacob Marinas__
