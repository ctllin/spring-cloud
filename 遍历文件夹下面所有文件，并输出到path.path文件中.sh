dir='E:\github\spring-cloud'
#!/bin/bash
function getdir(){
for element in `ls $1`
do
dir_or_file=$1"/"$element
if [ -d $dir_or_file ]
then
getdir $dir_or_file
else
echo $dir_or_file >> path.path
fi
done
}
root_dir="./"
getdir $root_dir
