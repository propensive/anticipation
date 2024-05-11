/*
    Anticipation, version [unreleased]. Copyright 2024 Jon Pretty, Propensive OÜ.

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

trait SpecificInstant:
  type Self
  def instant(millisecondsSinceEpoch: Long): Self

trait GenericInstant:
  type Self
  def millisecondsSinceEpoch(instant: Self): Long

trait SpecificDuration:
  type Self
  def duration(milliseconds: Long): Self

trait GenericDuration:
  type Self
  def milliseconds(duration: Self): Long

extension [InstantType](instant: InstantType)(using generic: GenericInstant { type Self = InstantType })
  def millisecondsSinceEpoch: Long = generic.millisecondsSinceEpoch(instant)

object SpecificInstant:
  def apply[InstantType](millisecondsSinceEpoch: Long)(using specific: SpecificInstant { type Self = InstantType }): InstantType =
    specific.instant(millisecondsSinceEpoch)

  given (SpecificInstant { type Self = Long }) = identity(_)

extension [DurationType](duration: DurationType)(using generic: GenericDuration { type Self = DurationType })
  def milliseconds: Long = generic.milliseconds(duration)

object SpecificDuration:
  def apply[DurationType](milliseconds: Long)(using specific: SpecificDuration { type Self = DurationType }): DurationType =
    specific.duration(milliseconds)

  given (SpecificDuration { type Self = Long }) = identity(_)

object GenericInstant:
  given (GenericInstant { type Self = Long }) = identity(_)

object GenericDuration:
  given (GenericDuration { type Self = Long }) = identity(_)
