#!/bin/sh

# echo "$VAULT_PASS" > ~/.vault_pass.txt
# echo "ANSIBLE"
# mkdir ~/.ssh
# ansible-vault --vault-password-file ~/.vault_pass.txt view ansible/ssh_key.txt.secret > ~/.ssh/id_rsa
# chmod 0600 ~/.ssh/id_rsa

ansible-playbook -e "build_sha=$GITHUB_SHA" ansible/ansible.yaml