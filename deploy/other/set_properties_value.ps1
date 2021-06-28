# Установить значение в properties-файл.
# Пример использования скрипта:
#    | ./set_properties_value.ps1 -filePath "./config.properties" -itemName "appName" -value "myApp"

param(
    # required
    # Путь до properties-файла
    [string] $filePath,
    # required
    # Наименование элемента для чтения
    [string] $itemName,
    # required
    # Значение
    [string] $value
)

if ($filePath -eq $null -Or $filePath -eq '') {
    throw "filePath is NULL"
}

if ($itemName -eq $null -Or $itemName -eq '') {
    throw "itemName is NULL"
}

$props = convertfrom-stringdata (get-content $filePath -raw)
$props."${itemName}" = $value
$props.GetEnumerator() | % { "$($_.Name) = $($_.Value)" } > $filePath