/**
 * 
 * @param fileRandomName
 * @param fileLogicName
 * @param fileLogicPath
 * @returns {MyFile}
 */
function MyFile(fileUniqueName,fileLogicName,fileLogicPath,fileImg,fileType){
	
	this.fileUniqueName = fileUniqueName;
	this.fileLogicName = fileLogicName;
	this.filePath = fileLogicPath;
	this.fileImg = fileImg;
	this.fileType = fileType;	
}