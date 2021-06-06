#    | ./format_xml.ps1 -filePath "./my.xml"

param(
    # required
    # example: ./app/pom.xml
    [string] $filePath
)

if ($filePath -eq $null -Or $filePath -eq '') {
	throw "xml filePath for update maven version is NULL"
}

if (!(Test-Path -Path "${filePath}")) {
    throw "file not found [${filePath}]"
}

[xml] $xmlContent = Get-Content $filePath

$XmlWriter = New-Object System.XMl.XmlTextWriter($filePath, $Null)
    
$xmlWriter.Formatting = 'Indented'
$xmlWriter.Indentation = 4
$XmlWriter.IndentChar = " "
    
$xmlContent.WriteContentTo($XmlWriter)
$XmlWriter.Flush()
$XmlWriter.Close()