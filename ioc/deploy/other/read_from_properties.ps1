# Прочитать значение из properties-файла.
# Пример использования скрипта:
#    | $itemValue = &"./read_from_properties.ps1" -filePath "./config.properties" -itemName "appName" | Select-Object -Last 1

param(
    # required
    # Путь до properties-файла
    [string] $filePath,
    # required
    # Наименование элемента для чтения
    [string] $itemName
)

if ($filePath -eq $null -Or $filePath -eq '') {
    throw "filePath is NULL"
}

if ($itemName -eq $null -Or $itemName -eq '') {
    throw "itemName is NULL"
}

$props = convertfrom-stringdata (get-content $filePath -raw)
return $props."${itemName}"