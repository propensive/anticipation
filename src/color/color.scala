/*
    Anticipation, version [unreleased]. Copyright 2023 Jon Pretty, Propensive OÜ.

    The primary distribution site is: https://propensive.com/

    Licensed under the Apache License, Version 2.0 (the "License"); you may not use this
    file except in compliance with the License. You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software distributed under the
    License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
    either express or implied. See the License for the specific language governing permissions
    and limitations under the License.
*/

package anticipation

import language.experimental.captureChecking

trait RgbColor[ColorType]:
  def rgb(color: ColorType): Int
  def red(color: ColorType): Int = rgb(color)&255
  def green(color: ColorType): Int = (rgb(color) >> 8)&255
  def blue(color: ColorType): Int = (rgb(color) >> 16)&255

extension [ColorType](color: ColorType)
  def asRgb24Int(using rgbColor: RgbColor[ColorType]): Int = rgbColor.rgb(color)
