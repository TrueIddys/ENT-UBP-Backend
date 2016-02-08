#!/bin/bash

# check the two lasts commits, and take the older one
#  (because github add a commit for who accept the PR)
function get_last_real_commiter() {
	local lrc=""
	for str in $(git log -2 --pretty=%cn)
	do
		lrc=$str
	done

	echo $lrc
}

function get_name_for_commiter() {
	echo $(printenv $1_NAME)
}

function get_pass_for_commiter() {
	echo $(printenv $1_PASS)
}


git checkout master

commiter=$(get_last_real_commiter)
echo $commiter

name=$(get_name_for_commiter $commiter)
pass=$(get_pass_for_commiter $commiter)

git remote add forgeubp https://$name:$pass@forge.clermont-universite.fr/git/web-services
git -c http.sslVerify=false push forgeubp master